package com.fileutil.MoveFile;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import java.net.MalformedURLException;

public class SecureTransferFiles {
    //source (file://LAPTOP-NCVRG7VT/source)
    public static void main(String[] args) throws MalformedURLException, SmbException {
//        String sourcePath = "smb://192.168.16.1/source/";
        String sourcePath = "smb://OTCFlatFile-NonProd.bp.com/OTCFlatFile-NonProd$/IL_Batch/ENDUR/Pending";
        String destPath = "smb://OTCFlatFile-NonProd.bp.com/OTCFlatFile-NonProd$/IL_Batch/ENDUR/Processing";
//        String destPath = "smb://192.168.16.1/dest/";

        System.out.println("source: " + sourcePath);
        System.out.println("destination: " + destPath);

        NtlmPasswordAuthentication sourceAuth = new NtlmPasswordAuthentication(null, "-svc-ist-xtx-cntrcti", "JO$3WNA7&Lmg1!");
        NtlmPasswordAuthentication destAuth = new NtlmPasswordAuthentication(null, "-svc-ist-xtx-cntrcti", "JO$3WNA7&Lmg1!");
        System.out.println("transferring files..");
        SmbFile dir = new SmbFile(sourcePath, sourceAuth);
        for (SmbFile f : dir.listFiles()) {
            System.out.println("transferring file " + f.getName());
            SmbFile destFile = new SmbFile(destPath + f.getName(), destAuth);
            f.copyTo(destFile);
            f.delete();
        }
    }
}
