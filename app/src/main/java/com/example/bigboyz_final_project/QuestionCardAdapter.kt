import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigboyz_final_project.QuestionCard
import com.example.bigboyz_final_project.R

class QuestionCardAdapter(private val questionCards: MutableList<QuestionCard>, private val recyclerView: RecyclerView) :
    RecyclerView.Adapter<QuestionCardAdapter.QuestionCardViewHolder>() {

    class QuestionCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionNumber: TextView = itemView.findViewById(R.id.questionNumber)
        val question: EditText = itemView.findViewById(R.id.question)
        val wrongAnswer1: EditText = itemView.findViewById(R.id.wrong_answer_1)
        val wrongAnswer2: EditText = itemView.findViewById(R.id.wrong_answer_2)
        val wrongAnswer3: EditText = itemView.findViewById(R.id.wrong_answer_3)
        val correctAnswer: EditText = itemView.findViewById(R.id.correct_answer)
        val removeQuestion: View = itemView.findViewById(R.id.removeQuestion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question_card, parent, false)
        return QuestionCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionCardViewHolder, position: Int) {
        val questionCard = questionCards[position]
        holder.question.setText(questionCard.question)
        holder.wrongAnswer1.setText(questionCard.wrongAnswer1)
        holder.wrongAnswer2.setText(questionCard.wrongAnswer2)
        holder.wrongAnswer3.setText(questionCard.wrongAnswer3)
        holder.correctAnswer.setText(questionCard.correctAnswer)

        holder.questionNumber.text = "${holder.questionNumber.text} ${position + 1}"


        holder.removeQuestion.setOnClickListener {
            questionCards.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, questionCards.size)
        }
    }



    override fun getItemCount() = questionCards.size

    fun getQuestionCards(): List<QuestionCard> {
        return questionCards
    }

    fun addQuestionCard(questionCard: QuestionCard) {
        questionCards.add(questionCard)
        notifyItemInserted(questionCards.size - 1)
    }

    fun updateQuestionCards() {
        for (i in questionCards.indices) {
            val holder = recyclerView.findViewHolderForAdapterPosition(i) as QuestionCardViewHolder
            questionCards[i].question = holder.question.text.toString()
            questionCards[i].wrongAnswer1 = holder.wrongAnswer1.text.toString()
            questionCards[i].wrongAnswer2 = holder.wrongAnswer2.text.toString()
            questionCards[i].wrongAnswer3 = holder.wrongAnswer3.text.toString()
            questionCards[i].correctAnswer = holder.correctAnswer.text.toString()
        }
    }
}