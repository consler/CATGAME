/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2025 The Catrobat Team
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

package org.catrobat.catroid.builder;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.common.FlavoredConstants;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.exceptions.LoadingProjectException;
import org.catrobat.catroid.exceptions.ProjectException;
import org.catrobat.catroid.io.XstreamSerializer;
import org.catrobat.catroid.stage.StageActivity;

import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.io.IOException;

public class run
{
	public static void r(Context context) throws IOException, ProjectException
	{
		File catgame = new File(context.getCacheDir(), "CATGAME"); // file to check whether this is the first time loading the app

		if(catgame.exists())
		{
			initiate(context);

		}
		else
		{
			copy.copyAssetFolder(context, "CATGAME");
			catgame.createNewFile();
			new File(FlavoredConstants.DEFAULT_ROOT_DIRECTORY, "CATGAME/DeviceVariables.json").delete(); // we dont want to keep the device variables from the app distributor
			new File(FlavoredConstants.DEFAULT_ROOT_DIRECTORY, "CATGAME/DeviceLists.json").delete();

			initiate(context);

		}

	}

	private static void initiate(Context context) throws LoadingProjectException, IOException
	{
		File projectDir = new File(FlavoredConstants.DEFAULT_ROOT_DIRECTORY, "CATGAME");
		Project project = XstreamSerializer.getInstance().loadProject(projectDir, context);
		ProjectManager.getInstance().setCurrentProject(project);
		Intent intent = new Intent(context, StageActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);

	}

}
