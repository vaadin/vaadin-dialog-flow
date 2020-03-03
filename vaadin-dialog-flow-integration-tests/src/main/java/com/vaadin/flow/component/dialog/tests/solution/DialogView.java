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
package com.vaadin.flow.component.dialog.tests.solution;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

/**
 * Page created for testing purposes. Not suitable for demos.
 *
 * @author Vaadin Ltd.
 *
 */
@Route("solution/dialog")
public class DialogView extends Div {

    public DialogView() {
       // TASK 1: Create a dialog and add some text content to it
       Dialog dialog = new Dialog();
       dialog.add(new Text("This is a text"));

       // TASK 2: Create a button that opens the dialog on click
       Button button = new Button("open dialog", e -> dialog.open());
       add(button);

       // TASK 3: Make dialog resizable
       dialog.setResizable(true);

       // TASK 4: Make dialog draggable
       dialog.setDraggable(true);

       // TASK 5: Add a second dialog and configure both dialogs so that they 
       // can be opened at the same time
       Dialog secondDialog = new Dialog(new Text("A second dialog"));
       secondDialog.setDraggable(true);
       Button secondButton = new Button("open second dialog",
                e -> secondDialog.open());
       add(secondButton);

       dialog.setModal(false);
       secondDialog.setModal(false);

       // TASK 6: (nice to have) make sure both dialogs can be closed by the user
       dialog.add(new Button("close", e -> dialog.close()));
       secondDialog.add(new Button("close", e -> secondDialog.close()));

    }
}
