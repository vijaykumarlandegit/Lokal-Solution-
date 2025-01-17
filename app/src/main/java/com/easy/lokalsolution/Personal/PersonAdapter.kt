package com.easy.lokalsolution.Personal

/*
class PersonAdapter(private val onPersonClick: PersonEntity) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private var personList = listOf<PersonEntity>()

    fun setPersons(persons: List<PersonEntity>) {
        personList = persons
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.bind(person)
        holder.itemView.setOnClickListener {
            onPersonClick(person) // Notify when a person is clicked
        }
    }

    override fun getItemCount(): Int = personList.size

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.personName)
        private val ageTextView: TextView = itemView.findViewById(R.id.personAge)
        private val employeeTextView: TextView = itemView.findViewById(R.id.personEmployee)

        fun bind(person: PersonEntity) {
            nameTextView.text = person.name
            ageTextView.text = "Age: ${person.age}"
            employeeTextView.text = if (person.isEmployee) "Employee" else "Not Employee"
        }
    }
}
*/
