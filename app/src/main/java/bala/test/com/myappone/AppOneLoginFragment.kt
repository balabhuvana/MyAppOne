package bala.test.com.myappone


import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_app_one_login.*


/**
 * A simple [Fragment] subclass.
 * Use the [AppOneLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AppOneLoginFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mInsertBtn: Button? = null
    private var mRetriveBtn: Button? = null
    private var TAG: String = AppOneHomeFragment.javaClass.simpleName

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1)
            mParam2 = getArguments().getString(ARG_PARAM2)
        }
    }

    public override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_app_one_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AppOneLoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): AppOneLoginFragment {
            val fragment = AppOneLoginFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.setArguments(args)
            return fragment
        }
    }

    fun initView(view: View?) {
        mInsertBtn = view!!.findViewById<Button>(R.id.btnInsert) as Button
        mInsertBtn!!.setOnClickListener(View.OnClickListener {
            onClickAddName()
            fragmentManager.beginTransaction().replace(R.id.frameLayoutFO, AppOneHomeFragment.newInstance("", "")).commit()
        })


        mRetriveBtn = view!!.findViewById<Button>(R.id.btnRetrive) as Button

        mRetriveBtn!!.setOnClickListener(View.OnClickListener {
            onClickRetrieveStudents()
        })
    }

    fun onClickAddName() {
        // Add a new student record
        val values = ContentValues()
        values.put(StudentsProvider.NAME, etUsername.text.toString())

        values.put(StudentsProvider.GRADE, etPassword.text.toString())

        val uri = activity.getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values)

        Log.d(TAG, "" + uri.toString())
        Toast.makeText(activity,
                uri.toString(), Toast.LENGTH_LONG).show()
    }

    fun onClickRetrieveStudents() {
        // Retrieve student records
        val URL = "content://bala.test.com.myappone.StudentsProvider"

        val students = Uri.parse(URL)
        val c = activity.managedQuery(students, null, null, null, "name")

        if (c.moveToFirst()) {
            do {
                Log.d(TAG, "" + c.getString(c.getColumnIndex(StudentsProvider._ID)))
                Log.d(TAG, "" + c.getString(c.getColumnIndex(StudentsProvider.NAME)))
                Log.d(TAG, "" + c.getString(c.getColumnIndex(StudentsProvider.GRADE)))
                Toast.makeText(activity,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show()
            } while (c.moveToNext())
        }
    }

}// Required empty public constructor
