/*
 * Copyright (C) 2013 readyState Software Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.readystatesoftware.systembartint.sample;

import android.view.View;
import android.widget.Toast;

public class MatchActionBarActivity extends StatusBarActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_match_actionbar;
    }

    @Override
    public void init() {
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.green));

        findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MatchActionBarActivity.this, "toast", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
