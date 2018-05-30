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

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.router.Route;

/**
 * Page created for testing purposes. Not suitable for demos.
 * 
 * @author Vaadin Ltd.
 *
 */
@Route("dialog-with-error-dialog-test")
public class DialogWithErrorDialogTestPage extends Div {

    public DialogWithErrorDialogTestPage() {
        Dialog dialog = new Dialog();

        Div message = new Div();
        NativeButton updateButton = new NativeButton("Update",
                e -> message.setText("Updated"));
        updateButton.setId("update");

        NativeButton errorButton = new NativeButton("Error",
                e -> System.out.println(1 / 0));
        errorButton.setId("error");
        dialog.add(errorButton);

        NativeButton openDialogButton = new NativeButton("Open dialog",
                e -> dialog.open());
        openDialogButton.setId("open-dialog");

        add(message, updateButton, openDialogButton);

    }

}
