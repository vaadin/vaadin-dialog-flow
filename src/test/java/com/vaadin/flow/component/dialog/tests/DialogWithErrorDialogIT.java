/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.dialog.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;

/**
 * Test that the 'Internal error' dialog can be closed even if there's a Dialog
 * opened: https://github.com/vaadin/flow/issues/4089
 * 
 * @author Vaadin Ltd.
 */
@TestPath("dialog-with-error-dialog-test")
public class DialogWithErrorDialogIT extends AbstractComponentIT {

    @Before
    public void init() {
        open();
        findElement(By.id("update")).click();
        findElement(By.id("open-dialog")).click();
    }

    @Test
    public void openDialog_causeInternalError_errorDialogIsClickable() {
        findElement(By.id("error")).click();

        findElement(By.className("v-system-error")).click();

        try {
            waitUntil(driver -> !isMessageUpdated());
        } catch (TimeoutException e) {
            Assert.fail("Clicking the 'Internal error' notification should "
                    + "refresh the page, resetting the state of the UI");
        }
    }

    @Test
    public void openDialog_causeInternalError_errorDialogIsClosableWithEsc() {
        findElement(By.id("error")).click();

        waitUntil(driver -> isElementPresent(By.className("v-system-error")));

        new Actions(getDriver()).sendKeys(Keys.ESCAPE).build().perform();

        try {
            waitUntil(driver -> !isMessageUpdated());
        } catch (TimeoutException e) {
            Assert.fail("Clicking the esc-key after an internal error should "
                    + "refresh the page, resetting the state of the UI");
        }
    }

    private boolean isMessageUpdated() {
        return "Updated".equals(findElement(By.id("message")).getText());
    }
}
