import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color
import com.example.mylistingapp.ItemClickListner
import com.example.mylistingapp.R


class ListAdapter(
    private val listItems: List<ListItem>,
    private val listener: ItemClickListner,
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = listItems[position]
        holder.bind(listItem, listener, position)
    }

    override fun getItemCount(): Int = listItems.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        private val subtitleTextView = itemView.findViewById<TextView>(R.id.subtitleTextView)

        @SuppressLint("SetTextI18n")
        fun bind(listItem: ListItem, listener: ItemClickListner, position: Int) {
            titleTextView.text = listItem.title
            subtitleTextView.text = listItem.subtitle

            if (listItem.isSelected) {
                titleTextView.text = "Title Changed"
                titleTextView.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                titleTextView.text = listItem.title
                titleTextView?.setTextColor(Color.parseColor("#FEEFBA"))
            }

            itemView.setOnClickListener {
                listener.onClick(listItem, position)
            }
        }
    }
}


