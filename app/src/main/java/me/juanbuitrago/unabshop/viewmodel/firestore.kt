package me.juanbuitrago.unabshop.viewmodel

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import me.juanbuitrago.unabshop.model.Producto

class FirestoreHelper {
    private val db = Firebase.firestore

    fun agregarProducto(producto: Producto, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        db.collection("productos")
            .add(producto)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onError(e) }
    }

    fun obtenerProductos(callback: (List<Producto>) -> Unit) {
        db.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                val productos = result.map { doc ->
                    doc.toObject(Producto::class.java).copy(id = doc.id)
                }
                callback(productos)
            }
    }

    fun eliminarProducto(id: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        db.collection("productos").document(id)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onError(e) }
    }
}