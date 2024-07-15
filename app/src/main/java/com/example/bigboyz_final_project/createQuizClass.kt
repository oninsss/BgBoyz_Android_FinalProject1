package com.example.bigboyz_final_project

import androidx.appcompat.app.AppCompatActivity

class createQuizClass : AppCompatActivity{

    private lateinit var quizName: String
    private lateinit var quizQuestions: ArrayList<Question>
    private lateinit var quizAnswers: ArrayList<Answer>

    constructor(quizName: String, quizQuestions: ArrayList<Question>){
        this.quizName = quizName
        this.quizQuestions = quizQuestions
    }

    fun getQuizName(): String{
        return quizName
    }

    fun getQuizQuestions(): ArrayList<Question>{
        return quizQuestions
    }

    fun addQuestion(question: Question){
        quizQuestions.add(question)
    }

    fun removeQuestion(question: Question){
        quizQuestions.remove(question)
    }

    fun editQuestion(question: Question, newQuestion: String, newAnswer: String){
        for (i in 0 until quizQuestions.size){
            if (quizQuestions.get(i).getQuestion().equals(question.getQuestion())){
                quizQuestions.get(i).setQuestion(newQuestion)
                quizAnswers.get(i).setAnswer(newAnswer)
                break
            }
        }
    }

    fun getQuestion(index: Int): Question{
        return quizQuestions.get(index)
    }

    fun getAnswer(index: Int): Answer{
        return quizAnswers.get(index)
    }

    fun getNumQuestions(): Int{
        return quizQuestions.size
    }

    fun getQuiz(): createQuizClass{
        return this
    }

    fun setQuizName(quizName: String){
        this.quizName = quizName
    }


    fun setQuiz(quiz: createQuizClass){
        this.quizName = quiz.getQuizName()
        this.quizQuestions = quiz.getQuizQuestions()
    }

    fun clearQuestions(){
        quizQuestions.clear()
    }

    fun clearQuestion(index: Int){
        quizQuestions.removeAt(index)
    }

    fun clearQuestion(question: Question){
        quizQuestions.remove(question)
    }

    fun clearQuestion(question: String){
        for (i in 0 until quizQuestions.size){
            if (quizQuestions.get(i).getQuestion().equals(question)){
                quizQuestions.removeAt(i)
                break
            }
        }
    }

    fun clearAnswer(questionID: Int){
        for (i in 0 until quizQuestions.size){
            if (questionID == quizQuestions.get(i).getQuestionID()){
                for (j in 0 until quizAnswers.size){
                    if (quizAnswers.get(j).getQuestionID() == questionID){
                        quizAnswers.removeAt(j)
                        break
                    }
                }
                break
            }
        }
    }




}