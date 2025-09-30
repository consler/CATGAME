package org.catrobat.catroid.builder;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// this entire thing is ai generated but it works
public class copy {
	private static final String TAG = "AssetFolderCopier";

	public static void copyAssetFolder(Context context, String assetFolder) throws IOException {
		AssetManager assetManager = context.getAssets();
		String[] assets = assetManager.list(assetFolder);
		if (assets == null || assets.length == 0) {
			// It's a file
			copyAssetFile(context, assetFolder);
		} else {
			// It's a folder: create the target directory
			File outDir = new File(context.getFilesDir(), assetFolder);
			if (!outDir.exists()) {
				if (!outDir.mkdirs()) {
					throw new IOException("Failed to create directory: " + outDir.getAbsolutePath());
				}
			}

			// Recursively copy each child
			for (String asset : assets) {
				String childAssetPath = assetFolder + File.separator + asset;
				copyAssetFolder(context, childAssetPath);
			}
		}
	}

	private static void copyAssetFile(Context context, String assetPath) throws IOException {
		AssetManager assetManager = context.getAssets();
		InputStream in = assetManager.open(assetPath);
		File outFile = new File(context.getFilesDir(), assetPath);

		// Ensure parent directories exist
		File parent = outFile.getParentFile();
		if (parent != null && !parent.exists()) {
			if (!parent.mkdirs()) {
				throw new IOException("Failed to create directory: " + parent.getAbsolutePath());
			}
		}

		OutputStream out = new FileOutputStream(outFile);

		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}

		in.close();
		out.flush();
		out.close();

		Log.d(TAG, "Copied asset file: " + assetPath + " to " + outFile.getAbsolutePath());
	}
}