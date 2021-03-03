package csci2020u.lab05;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentRecord {
    SimpleStringProperty SId;
    SimpleFloatProperty midterm;
    SimpleFloatProperty assign;
    SimpleFloatProperty finalEx;
    SimpleFloatProperty finMark;
    SimpleStringProperty letterGrade;

    public StudentRecord(String sid, float assn, float mid, float finEx){
        this.SId = new SimpleStringProperty(sid);
        this.midterm = new SimpleFloatProperty(mid);
        this.assign = new SimpleFloatProperty(assn);
        this.finalEx = new SimpleFloatProperty(finEx);
        this.finMark = new SimpleFloatProperty(calcFinalMark(getMidterm(), getAssign(), getFinalEx()));
        this.letterGrade = new SimpleStringProperty(calcLetterGrade(getFinMark()));
    }
    public String getSId(){
        return SId.get();
    }
    public void setSId(String sid){
        SId.set(sid);
    }
    public float getMidterm(){
        return midterm.get();
    }
    public void setMidterm(float mid){
        midterm.set(mid);
    }
    public float getAssign(){
        return assign.get();
    }
    public void setAssign(float assi){
        assign.set(assi);
    }
    public float getFinalEx(){
        return finalEx.get();
    }
    public void setFinalEx(float fin){
        finalEx.set(fin);
    }
    public float getFinMark(){
        return finMark.get();
    }
    public void setFinMark(float f){
        finMark.set(f);
    }
    public String getLetterGrade(){
        return letterGrade.get();
    }
    public void setLetterGrade(String l){
        letterGrade.set(l);
    }
    protected float calcFinalMark(float a, float b, float c){
        float asng = 0.2f*b;
        float mid = 0.3f*a;
        float fin = 0.5f*c;
        return(asng+mid+fin);

    }
    protected String calcLetterGrade(float x){
        if(x >= 80f){
            return "A";
        }
        else if(x>=70f && x<80f){
            return "B";
        }
        else if(x>= 60f && x<70f){
            return "C";
        }
        else if(x>= 50f && x<60f){
            return "D";
        }
        else{
            return "F";
        }
    }

};

