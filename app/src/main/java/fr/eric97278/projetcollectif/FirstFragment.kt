package fr.eric97278.projetcollectif

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import fr.eric97278.projetcollectif.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtenir la référence de la SearchView depuis le binding
        val searchView = binding.searchView // Assurez-vous que l'id est correct

        // Configurer le listener pour gérer les événements de recherche
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    // Exécuter une recherche avec le texte soumis
                    performSearch(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    // Exécuter une action à chaque changement de texte, comme filtrer une liste
                    performSearch(it)
                }
                return true
            }
        })
    }

    private fun performSearch(query: String) {
        // Implémentez ici la logique de recherche, par exemple :
        // 1. Filtrer une liste
        // 2. Interroger une base de données
        // 3. Faire une requête réseau
        Log.d("FirstFragment", "Recherche pour : $query")
        // Ajoutez votre logique de recherche ici
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
