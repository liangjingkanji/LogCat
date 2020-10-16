/*
 * Copyright (C) 2018 Drake, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.drake.logcat.sample.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.drake.logcat.LogCat
import com.drake.logcat.sample.R

class LogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_log, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_verbose -> LogCat.v("Verbose")
            R.id.menu_debug -> LogCat.v("Debug")
            R.id.menu_info -> LogCat.v("Info")
            R.id.menu_warn -> LogCat.v("Warn")
            R.id.menu_error -> LogCat.v("Error")
            R.id.menu_assert -> LogCat.v("Assert")
            R.id.menu_json -> LogCat.json(getString(R.string.json))
        }
        return super.onOptionsItemSelected(item)
    }
}