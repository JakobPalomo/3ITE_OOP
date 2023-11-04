package com.example.a3ite_oop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class FragmentClass : DialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView : View = inflater.inflate(R.layout.activity_fragment,container, false)
        var bundle_argument = arguments


        var FN = bundle_argument!!.getString("bundleFN")
        var LN = bundle_argument!!.getString("bundleLN")
        var PW = bundle_argument!!.getString("bundlePW")
        var Date = bundle_argument!!.getString("bundleDate")
        var EM = bundle_argument!!.getString("bundleEM")
        var UN = bundle_argument!!.getString("bundleUN")
        var MB = bundle_argument!!.getString("bundleMB")
        var AC = bundle_argument!!.getString("bundleAcc")

        var textFirstValue : TextView = rootView.findViewById(R.id.txtFN)
        var textSecondValue : TextView = rootView.findViewById(R.id.txtLN)
        var PW_ : TextView = rootView.findViewById(R.id.txtPW)
        var Date_ : TextView = rootView.findViewById(R.id.txtDate)
        var EM_ : TextView = rootView.findViewById(R.id.txtEM)
        var UN_ : TextView = rootView.findViewById(R.id.txtUN)
        var MB_ : TextView = rootView.findViewById(R.id.txtMB)
        var AC_ : TextView = rootView.findViewById(R.id.txtAcc)

        var buttonDismiss : Button = rootView.findViewById(R.id.btnDismiss)
        var buttonConfirm : Button = rootView.findViewById(R.id.btnConfirmS)

        textFirstValue.setText(FN.toString())
        textSecondValue.setText(LN.toString())
        PW_.setText(PW.toString())
        Date_.setText(Date.toString())
        EM_.setText(EM.toString())
        UN_.setText(UN.toString())
        MB_.setText(MB.toString())
        AC_.setText(AC.toString())

        buttonDismiss.setOnClickListener {
            dismiss()
        }

        buttonConfirm.setOnClickListener {
            dismiss()
        }

        return rootView;
    }

}