package com.dkv.taskcallback;

public interface ConfirmationDialogCallback {

    void onConfirmationDialogPositiveButtonClicked(int mDialogID);

    void onConfirmationDialogNegativeButtonClicked(int mDialogID);
}
