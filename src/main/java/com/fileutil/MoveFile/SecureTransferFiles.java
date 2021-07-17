package com.fileutil.MoveFile;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

public class SecureTransferFiles {
    public void moveFilesFromPendingToProcessing() {
        String sourcePath = "smb://OTCFlatFile-NonProd.bp.com/OTCFlatFile-NonProd$/IL_Batch/ENDUR/Pending/";
        String destPath = "smb://OTCFlatFile-NonProd.bp.com/OTCFlatFile-NonProd$/IL_Batch/ENDUR/Processing/";
        moveFilesFromSourceToDest(sourcePath, destPath);
    }

    public void moveFilesFromProcessingToComplete() {
        String sourcePath = "smb://OTCFlatFile-NonProd.bp.com/OTCFlatFile-NonProd$/IL_Batch/ENDUR/Processing/";
        String destPath = "smb://OTCFlatFile-NonProd.bp.com/OTCFlatFile-NonProd$/IL_Batch/ENDUR/Complete/";

        moveFilesFromSourceToDest(sourcePath, destPath);
    }

    public void moveFilesFromSourceToDest(String sourcePath, String destPath) {
        try {

            NtlmPasswordAuthentication sourceAuth = new NtlmPasswordAuthentication(null, "username", "pwd");
            SmbFile file = new SmbFile(sourcePath, sourceAuth);
            System.out.println("source directory: " + sourcePath);
            System.out.println("destination directory: " + destPath);
            for (SmbFile f : file.listFiles()) {
                System.out.println("transferring file: " + f);
                System.out.println("source path: " + f);
                SmbFile dest = new SmbFile(destPath + f.getName(), sourceAuth);
                System.out.println("  copy file to destination: " + dest);
                f.copyTo(dest);
                System.out.println("  copied file to destination: " + dest);
                System.out.println("  delete file from source: " + f);
                f.delete();
                System.out.println("  deleted file from source: " + f);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
