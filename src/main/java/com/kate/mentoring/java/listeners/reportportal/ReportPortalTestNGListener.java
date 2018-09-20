package com.kate.mentoring.java.listeners.reportportal;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public class ReportPortalTestNGListener extends BaseTestNGListener {

        private static final Supplier<ITestNGService> SERVICE = Suppliers.memoize(new Supplier<ITestNGService>() {
            @Override
            public ITestNGService get() {
                return new TestNGService();
            }
        });

	public ReportPortalTestNGListener() {
            super(SERVICE.get());
        }
}
