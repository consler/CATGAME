/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2021 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.ui

import android.os.Bundle
import android.preference.PreferenceManager
import org.catrobat.catroid.ProjectManager
import org.catrobat.catroid.R
import org.catrobat.catroid.common.Survey
import org.catrobat.catroid.databinding.ActivityMainMenuBinding
import org.catrobat.catroid.ui.settingsfragments.SettingsFragment
import org.catrobat.catroid.utils.ScreenValueHandler
import org.koin.android.ext.android.inject
import org.catrobat.catroid.builder.run

private const val SDK_VERSION = 24

class MainMenuActivity : BaseCastActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SettingsFragment.setToChosenLanguage(this)

        PreferenceManager.setDefaultValues(this, R.xml.preferences, true)
        PreferenceManager.setDefaultValues(this, R.xml.nxt_preferences, true)
        PreferenceManager.setDefaultValues(this, R.xml.ev3_preferences, true)
        ScreenValueHandler.updateScreenWidthAndHeight(this)

        run.r(applicationContext)
    }

    companion object {
        val TAG = MainMenuActivity::class.java.simpleName

        @JvmField
        var surveyCampaign: Survey? = null
    }
}
