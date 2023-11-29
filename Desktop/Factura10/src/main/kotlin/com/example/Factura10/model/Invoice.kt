package com.example.Factura10.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "invoice")
class Invoice{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var code: String? = null   //description_one en la base de datos
    var create_at: Date? = null   //address
    var total: Double? = null
    var client_id: Long? = null

}