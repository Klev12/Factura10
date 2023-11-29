package com.example.Factura10.service

import com.example.Factura10.model.Detail
import com.example.Factura10.repository.DetailRepository
import com.example.Factura10.repository.InvoiceRepository
import com.example.Factura10.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    fun list ():List<Detail>{
        return detailRepository.findAll()
    }
    fun save(detail: Detail): Detail{
        try{

            invoiceRepository.findById(detail.invoice_id)
                    ?: throw Exception("Id invoice no encontrada")
            productRepository.findById(detail.product_id)
                    ?: throw Exception("Id product no encontrada")
            return detailRepository.save(detail)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(detail: Detail): Detail{
        try {
            detailRepository.findById(detail.id)
                    ?: throw Exception("ID no existe")

            return detailRepository.save(detail)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(detail: Detail): Detail{
        try{
            val response = detailRepository.findById(detail.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                quantity=detail.quantity //un atributo del modelo
            }
            return detailRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Detail?{
        return detailRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = detailRepository.findById(id)
                    ?: throw Exception("ID no existe")
            detailRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


}