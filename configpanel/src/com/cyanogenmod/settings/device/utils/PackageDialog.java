package com.cyanogenmod.settings.device.utils;

import android.os.Bundle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import java.util.List;
import android.content.Context;

public class PackageDialog extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);
		CharSequence[] packageNames = new String[packs.size()+1];
		CharSequence[] hrblPackageNames = new String[packs.size()+1];
		packageNames[0] = "";
		hrblPackageNames[0] = "Default action";
		for(int i = 0; i < packs.size(); i++){
			packageNames[i+1] = packs.get(i).packageName;
			hrblPackageNames[i+1] = packs.get(i).applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
		}		
		final CharSequence[] finalPackageNames = packageNames;
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Choose launch action")
			.setItems(hrblPackageNames, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				   // The 'which' argument contains the index position
				   // of the selected item
				   String selectedPackageName;
				   if(which == 0){
					   selectedPackageName = "";					   
				   } else{
					   selectedPackageName = finalPackageNames[which].toString();
				   }
				   FileUtils.writeLine("/data/data/com.cyanogenmod.settings.device/LaunchPackage",selectedPackageName);
               }
			});;
		// Create the AlertDialog object and return it
		return builder.create();
	}
}
