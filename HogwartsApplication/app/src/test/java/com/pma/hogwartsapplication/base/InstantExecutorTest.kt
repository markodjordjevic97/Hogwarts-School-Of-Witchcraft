package com.appcrafters.brewery.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class InstantExecutorTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()  // androidx.arch.core:core-testing:2.1.0 - background executor,
                                            // zamenjuje je ih, i koristi synchrono izvrsavanje za testing
}