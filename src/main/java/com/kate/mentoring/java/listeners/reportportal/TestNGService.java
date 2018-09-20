package com.kate.mentoring.java.listeners.reportportal;

import com.epam.reportportal.annotations.ParameterKey;
import com.epam.reportportal.annotations.UniqueID;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.listeners.Statuses;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.ParameterResource;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.issue.Issue;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import com.epam.ta.reportportal.ws.model.log.SaveLogRQ;
import io.reactivex.Maybe;
import org.testng.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.internal.ConstructorOrMethod;
import rp.com.google.common.annotations.VisibleForTesting;
import rp.com.google.common.base.Function;
import rp.com.google.common.base.Supplier;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static rp.com.google.common.base.Optional.fromNullable;
import static rp.com.google.common.base.Strings.isNullOrEmpty;
import static rp.com.google.common.base.Throwables.getStackTraceAsString;

public class TestNGService implements ITestNGService {

    public static final String NOT_ISSUE = "NOT_ISSUE";
    public static final String RP_ID = "rp_id";
    public static final String ARGUMENT = "arg";

    private final AtomicBoolean isLaunchFailed = new AtomicBoolean();

    private MemoizingSupplier<Launch> launch;

    public TestNGService() {
        this.launch = new MemoizingSupplier<Launch>(new Supplier<Launch>() {
            @Override
            public Launch get() {
                final ReportPortal reportPortal = ReportPortal.builder().build();
                StartLaunchRQ rq = buildStartLaunchRq(reportPortal.getParameters());
                rq.setStartTime(Calendar.getInstance().getTime());
                return reportPortal.newLaunch(rq);
            }
        });
    }

    public TestNGService(Supplier<Launch> launch) {
        this.launch = new MemoizingSupplier<Launch>(launch);
    }

    @Override
    public void startLaunch() {
        this.launch.get().start();
    }

    @Override
    public void finishLaunch() {
        FinishExecutionRQ rq = new FinishExecutionRQ();
        rq.setEndTime(Calendar.getInstance().getTime());
        rq.setStatus(isLaunchFailed.get() ? Statuses.FAILED : Statuses.PASSED);
        launch.get().finish(rq);

        this.launch.reset();

    }

    @Override
    public synchronized void startTestSuite(ISuite suite) {
        StartTestItemRQ rq = buildStartSuiteRq(suite);
        final Maybe<String> item = launch.get().startTestItem(rq);
        suite.setAttribute(RP_ID, item);
    }

    @Override
    public synchronized void finishTestSuite(ISuite suite) {
        if (null != suite.getAttribute(RP_ID)) {
            FinishTestItemRQ rq = buildFinishTestSuiteRq(suite);
            launch.get().finishTestItem(this.<Maybe<String>>getAttribute(suite, RP_ID), rq);
            suite.removeAttribute(RP_ID);
        }
    }

    @Override
    public void startTest(ITestContext testContext) {
        if (hasMethodsToRun(testContext)) {
            StartTestItemRQ rq = buildStartTestItemRq(testContext);
            final Maybe<String> testID = launch.get().startTestItem(this.<Maybe<String>>getAttribute(testContext.getSuite(), RP_ID), rq);
            testContext.setAttribute(RP_ID, testID);
        }
    }

    @Override
    public void finishTest(ITestContext testContext) {
        if (hasMethodsToRun(testContext)) {
            FinishTestItemRQ rq = buildFinishTestRq(testContext);
            launch.get().finishTestItem(this.<Maybe<String>>getAttribute(testContext, RP_ID), rq);
        }
    }

    @Override
    public void startTestMethod(ITestResult testResult) {
        StartTestItemRQ rq = buildStartStepRq(testResult);
        if (rq == null) {
            return;
        }

        Maybe<String> stepMaybe = launch.get().startTestItem(this.<Maybe<String>>getAttribute(testResult.getTestContext(), RP_ID), rq);
        testResult.setAttribute(RP_ID, stepMaybe);
    }

    @Override
    public void finishTestMethod(String status, ITestResult testResult) {
        if (Statuses.SKIPPED.equals(status) && !isRetry(testResult) && null == testResult.getAttribute(RP_ID)) {
            startTestMethod(testResult);
        }

        FinishTestItemRQ rq = buildFinishTestMethodRq(status, testResult);
        launch.get().finishTestItem(this.<Maybe<String>>getAttribute(testResult, RP_ID), rq);
    }

    @Override
    public void startConfiguration(ITestResult testResult) {
        TestMethodType type = TestMethodType.getStepType(testResult.getMethod());
        StartTestItemRQ rq = buildStartConfigurationRq(testResult, type);

        Maybe<String> parentId = getConfigParent(testResult, type);
        final Maybe<String> itemID = launch.get().startTestItem(parentId, rq);
        testResult.setAttribute(RP_ID, itemID);
    }

    @Override
    public void sendReportPortalMsg(final ITestResult result) {
        ReportPortal.emitLog(new Function<String, SaveLogRQ>() {
            @Override
            public SaveLogRQ apply(String itemId) {
                SaveLogRQ rq = new SaveLogRQ();
                rq.setTestItemId(itemId);
                rq.setLevel("ERROR");
                rq.setLogTime(Calendar.getInstance().getTime());
                if (result.getThrowable() != null) {
                    rq.setMessage(getStackTraceAsString(result.getThrowable()));
                } else {
                    rq.setMessage("Test has failed without exception");
                }
                rq.setLogTime(Calendar.getInstance().getTime());

                return rq;
            }
        });

    }

    protected StartTestItemRQ buildStartSuiteRq(ISuite suite) {
        StartTestItemRQ rq = new StartTestItemRQ();
        rq.setName(suite.getName());
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setType("SUITE");
        return rq;
    }


    protected StartTestItemRQ buildStartTestItemRq(ITestContext testContext) {
        StartTestItemRQ rq = new StartTestItemRQ();
        rq.setName(testContext.getName());
        rq.setStartTime(testContext.getStartDate());
        rq.setType("TEST");
        return rq;
    }

    protected StartLaunchRQ buildStartLaunchRq(ListenerParameters parameters) {
        StartLaunchRQ rq = new StartLaunchRQ();
        rq.setName(parameters.getLaunchName());
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setTags(parameters.getTags());
        rq.setMode(parameters.getLaunchRunningMode());
        if (!isNullOrEmpty(parameters.getDescription())) {
            rq.setDescription(parameters.getDescription());
        }
        return rq;
    }


    protected StartTestItemRQ buildStartConfigurationRq(ITestResult testResult, TestMethodType type) {
        StartTestItemRQ rq = new StartTestItemRQ();
        String configName = testResult.getMethod().getMethodName();
        rq.setName(configName);

        rq.setDescription(testResult.getMethod().getDescription());
        rq.setStartTime(new Date(testResult.getStartMillis()));
        rq.setType(type == null ? null : type.toString());
        return rq;
    }

    protected StartTestItemRQ buildStartStepRq(ITestResult testResult) {
        //		if (testResult.getAttribute(RP_ID) != null) {
        //			return null;
        //		}
        StartTestItemRQ rq = new StartTestItemRQ();
        String testStepName;
        if (testResult.getTestName() != null) {
            testStepName = testResult.getTestName();
        } else {
            testStepName = testResult.getMethod().getMethodName();
        }
        rq.setName(testStepName);

        rq.setDescription(createStepDescription(testResult));
        rq.setParameters(createStepParameters(testResult));
        rq.setUniqueId(extractUniqueID(testResult));
        rq.setStartTime(new Date(testResult.getStartMillis()));
        rq.setType(TestMethodType.getStepType(testResult.getMethod()).toString());

        rq.setRetry(isRetry(testResult));
        return rq;
    }


    protected FinishTestItemRQ buildFinishTestSuiteRq(ISuite suite) {
        /* 'real' end time */
        Date now = Calendar.getInstance().getTime();
        FinishTestItemRQ rq = new FinishTestItemRQ();
        rq.setEndTime(now);
        rq.setStatus(getSuiteStatus(suite));
        return rq;
    }

    protected FinishTestItemRQ buildFinishTestRq(ITestContext testContext) {
        FinishTestItemRQ rq = new FinishTestItemRQ();
        rq.setEndTime(testContext.getEndDate());
        String status = isTestPassed(testContext) ? Statuses.PASSED : Statuses.FAILED;
        rq.setStatus(status);
        return rq;
    }

    protected FinishTestItemRQ buildFinishTestMethodRq(String status, ITestResult testResult) {
        FinishTestItemRQ rq = new FinishTestItemRQ();
        rq.setEndTime(new Date(testResult.getEndMillis()));
        rq.setStatus(status);
        // Allows indicate that SKIPPED is not to investigate items for WS
        if (Statuses.SKIPPED.equals(status) && !fromNullable(launch.get().getParameters().getSkippedAnIssue()).or(false)) {
            Issue issue = new Issue();
            issue.setIssueType(NOT_ISSUE);
            rq.setIssue(issue);
        }
        return rq;
    }

    protected List<ParameterResource> createStepParameters(ITestResult testResult) {
        List<ParameterResource> parameters = Lists.newArrayList();
        Test testAnnotation = getMethodAnnotation(Test.class, testResult);
        Parameters parametersAnnotation = getMethodAnnotation(Parameters.class, testResult);
        if (null != testAnnotation && !isNullOrEmpty(testAnnotation.dataProvider())) {
            parameters = createDataProviderParameters(testResult);
        } else if (null != parametersAnnotation) {
            parameters = createAnnotationParameters(testResult, parametersAnnotation);
        }
        return parameters.isEmpty() ? null : parameters;
    }

    private List<ParameterResource> createAnnotationParameters(ITestResult testResult, Parameters parametersAnnotation) {
        List<ParameterResource> params = Lists.newArrayList();
        String[] keys = parametersAnnotation.value();
        Object[] parameters = testResult.getParameters();
        if (parameters.length != keys.length) {
            return params;
        }
        for (int i = 0; i < keys.length; i++) {
            ParameterResource parameter = new ParameterResource();
            parameter.setKey(keys[i]);
            parameter.setValue(parameters[i] != null ? parameters[i].toString() : null);
            params.add(parameter);
        }
        return params;
    }


    private List<ParameterResource> createDataProviderParameters(ITestResult testResult) {
        List<ParameterResource> result = Lists.newArrayList();
        Annotation[][] parameterAnnotations = testResult.getMethod().getConstructorOrMethod().getMethod().getParameterAnnotations();
        Object[] values = testResult.getParameters();
        int length = parameterAnnotations.length;
        if (length != values.length) {
            return result;
        }
        for (int i = 0; i < length; i++) {
            ParameterResource parameter = new ParameterResource();
            String key = ARGUMENT + i;
            String value = values[i] != null ? values[i].toString() : null;
            if (parameterAnnotations[i].length > 0) {
                for (int j = 0; j < parameterAnnotations[i].length; j++) {
                    Annotation annotation = parameterAnnotations[i][j];
                    if (annotation.annotationType().equals(ParameterKey.class)) {
                        key = ((ParameterKey) annotation).value();
                    }
                }
            }
            parameter.setKey(key);
            parameter.setValue(value);
            result.add(parameter);
        }
        return result;
    }


    protected String createStepDescription(ITestResult testResult) {
        StringBuilder stringBuffer = new StringBuilder();
        if (testResult.getMethod().getDescription() != null) {
            stringBuffer.append(testResult.getMethod().getDescription());
        }
        return stringBuffer.toString();
    }


    protected String getSuiteStatus(ISuite suite) {
        Collection<ISuiteResult> suiteResults = suite.getResults().values();
        String suiteStatus = Statuses.PASSED;
        for (ISuiteResult suiteResult : suiteResults) {
            if (!(isTestPassed(suiteResult.getTestContext()))) {
                suiteStatus = Statuses.FAILED;
                break;
            }
        }
        // if at least one suite failed launch should be failed
        isLaunchFailed.compareAndSet(false, suiteStatus.equals(Statuses.FAILED));
        return suiteStatus;
    }


    protected boolean isTestPassed(ITestContext testContext) {
        return testContext.getFailedTests().size() == 0 && testContext.getFailedConfigurations().size() == 0
                && testContext.getSkippedConfigurations().size() == 0 && testContext.getSkippedTests().size() == 0;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getAttribute(IAttributes attributes, String attribute) {
        return (T) attributes.getAttribute(attribute);
    }


    private String extractUniqueID(ITestResult testResult) {
        UniqueID itemUniqueID = getMethodAnnotation(UniqueID.class, testResult);
        return itemUniqueID != null ? itemUniqueID.value() : null;
    }


    private <T extends Annotation> T getMethodAnnotation(Class<T> annotation, ITestResult testResult) {
        ITestNGMethod testNGMethod = testResult.getMethod();
        if (null != testNGMethod) {
            ConstructorOrMethod constructorOrMethod = testNGMethod.getConstructorOrMethod();
            if (null != constructorOrMethod) {
                Method method = constructorOrMethod.getMethod();
                if (null != method) {
                    return method.getAnnotation(annotation);
                }
            }
        }
        return null;
    }

    private boolean hasMethodsToRun(ITestContext testContext) {
        return null != testContext && null != testContext.getAllTestMethods() && 0 != testContext.getAllTestMethods().length;
    }


    @VisibleForTesting
    Maybe<String> getConfigParent(ITestResult testResult, TestMethodType type) {
        Maybe<String> parentId;
        if (TestMethodType.BEFORE_SUITE.equals(type) || TestMethodType.AFTER_SUITE.equals(type)) {
            parentId = getAttribute(testResult.getTestContext().getSuite(), RP_ID);
        } else {
            parentId = getAttribute(testResult.getTestContext(), RP_ID);
        }
        return parentId;
    }

    private boolean isRetry(ITestResult result) {
        return result.getMethod().getRetryAnalyzer() != null;
    }

    @VisibleForTesting
    static class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        final Supplier<T> delegate;
        transient volatile boolean initialized;
        transient T value;
        private static final long serialVersionUID = 0L;

        MemoizingSupplier(Supplier<T> delegate) {
            this.delegate = delegate;
        }

        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = this.delegate.get();
                        this.value = t;
                        this.initialized = true;
                        return t;
                    }
                }
            }

            return this.value;
        }

        public synchronized void reset() {
            this.initialized = false;
        }

        public String toString() {
            return "Suppliers.memoize(" + this.delegate + ")";
        }
    }
}