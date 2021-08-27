package com.example.demo.model;

import java.util.ArrayList;
public class ScanContentResponse {
       // public String contentVersionId;
    private boolean result;
    private ArrayList<String> curseWords;

    public ScanContentResponse(boolean res, ArrayList<String> arrayWords) {
        this.result = res;
        this.curseWords = new ArrayList<String>(arrayWords);
    }

    public Boolean getresult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public void setCurseWords(ArrayList<String> words) {
        this.curseWords = new ArrayList<String>(words);
    }

    public ArrayList<String> getCurseWords() {
        return new ArrayList<String>(this.curseWords);
    }
}
