package com.example.moatasemabdallatif.math_quiz_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Question implements Parcelable{
    private int QID ;
    private Boolean QAnswer ;
    private Boolean isAnsweredTrue ;

    public Question() {
    }

    public Question(int QID, Boolean QAnswer) {
        this.QID = QID;
        this.QAnswer = QAnswer;
        isAnsweredTrue = false;
    }

    protected Question(Parcel in) {
        QID = in.readInt();
        byte tmpQAnswer = in.readByte();
        QAnswer = tmpQAnswer == 0 ? null : tmpQAnswer == 1;
        byte tmpIsAnsweredTrue = in.readByte();
        isAnsweredTrue = tmpIsAnsweredTrue == 0 ? null : tmpIsAnsweredTrue == 1;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
        /*
        public ArrayList<Question> newArrayList(int size) {
            return new ArrayList<Question>();
        }*/
    };

    public int getQID() {
        return QID;
    }

    public void setQID(int QID) {
        this.QID = QID;
    }

    public Boolean getQAnswer() {
        return QAnswer;
    }

    public void setQAnswer(Boolean QAnswer) {
        this.QAnswer = QAnswer;
    }

    public Boolean getAnsweredTrue() {
        return isAnsweredTrue;
    }

    public void setAnsweredTrue(Boolean answeredTrue) {
        isAnsweredTrue = answeredTrue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(QID);
        dest.writeByte((byte) (QAnswer == null ? 0 : QAnswer ? 1 : 2));
        dest.writeByte((byte) (isAnsweredTrue == null ? 0 : isAnsweredTrue ? 1 : 2));
    }

}
