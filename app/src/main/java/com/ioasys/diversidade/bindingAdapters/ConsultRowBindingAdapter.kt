package com.ioasys.diversidade.bindingAdapters

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.ioasys.diversidade.R

class ConsultRowBindingAdapter {

    companion object {
        @BindingAdapter("setStatus")
        @JvmStatic
        fun handleSetStatus(
            view: View,
            status: String
        ) {
            when (view) {
                is TextView -> {
                    when (status) {
                        "confirmed" -> {
                            view.text = "confirmado"
                            view.setTextColor(
                                ContextCompat.getColor(
                                    view.context,
                                    R.color.green
                                )
                            )
                        }
                        "canceled" -> {
                            view.text = "cancelado"
                            view.setTextColor(
                                ContextCompat.getColor(
                                    view.context,
                                    R.color.red
                                )
                            )
                        }
                        "pending" -> {
                            view.text = "solicitada"
                            view.setTextColor(
                                ContextCompat.getColor(
                                    view.context,
                                    R.color.blue
                                )
                            )
                        }
                        "concluded" -> {
                            view.text = "concluída"
                            view.setTextColor(
                                ContextCompat.getColor(
                                    view.context,
                                    R.color.green
                                )
                            )
                        }
                        "recused" -> {
                            view.text = "rejeitada"
                            view.setTextColor(
                                ContextCompat.getColor(
                                    view.context,
                                    R.color.red
                                )
                            )
                        }

                    }
                }
                is MaterialCardView -> {
                    when (status) {
                        "confirmed" -> {
                            view.strokeColor = ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        }
                        "canceled" -> {
                            view.strokeColor = ContextCompat.getColor(
                                view.context,
                                R.color.red
                            )
                        }
                        "pending" -> {
                            view.strokeColor = ContextCompat.getColor(
                                view.context,
                                R.color.blue
                            )
                        }
                        "concluded" -> {
                            view.strokeColor = ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        }
                        "recused" -> {
                            view.strokeColor = ContextCompat.getColor(
                                view.context,
                                R.color.red
                            )
                        }

                    }
                }
            }
        }
    }

}