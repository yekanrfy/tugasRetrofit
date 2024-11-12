import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.tugasretrofit.R
import com.example.tugasretrofit.model.User

class UserAdapter(private val context: Context, private val userList: List<User>) : BaseAdapter() {

    // Return the total count of items in the list
    override fun getCount(): Int {
        return userList.size
    }

    // Get the item at a specific position
    override fun getItem(position: Int): Any {
        return userList[position]
    }

    // Get the item ID for a specific position
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Inflating the layout and binding data to each view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false)

        // Get the user object at the current position
        val user = getItem(position) as User

        // Set name and email text
        val nameTextView: TextView = view.findViewById(R.id.tv_name)
        val emailTextView: TextView = view.findViewById(R.id.tv_email)
        val avatarImageView: ImageView = view.findViewById(R.id.img_avatar)

        nameTextView.text = "${user.firstName} ${user.lastName}"  // Combine first and last name
        emailTextView.text = user.email

        // Load avatar image with Glide
        Glide.with(context)
            .load(user.avatar)  // Use user.avatar (correct field name)
            .placeholder(R.drawable.ic_placeholder) // Optional: a placeholder while loading the image
            .error(R.drawable.ic_error) // Optional: an error image if loading fails
            .into(avatarImageView)

        return view
    }
}