import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ListeDemandeurs.css'; // Assurez-vous que le chemin est correct

const ListeDemandeurs = () => {
  const [demandeurs, setDemandeurs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [newDemandeur, setNewDemandeur] = useState({ username: '', email: '' });
  const [editingDemandeur, setEditingDemandeur] = useState(null);

  // Fonction pour récupérer les demandeurs
  const fetchDemandeurs = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/demandeurs/all');
      console.log('Réponse API:', response.data);
      setDemandeurs(response.data);
      setLoading(false);
    } catch (err) {
      console.error('Erreur API:', err);
      setError('Erreur lors de la récupération des demandeurs');
      setLoading(false);
    }
  };

  // Fonction pour ajouter un demandeur
  const addDemandeur = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/demandeurs/add', newDemandeur);
      setDemandeurs([...demandeurs, response.data]);
      setNewDemandeur({ username: '', email: '' });
    } catch (err) {
      console.error('Erreur API:', err);
      setError('Erreur lors de l\'ajout du demandeur');
    }
  };

  // Fonction pour supprimer un demandeur
  const deleteDemandeur = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/demandeurs/${id}`);
      setDemandeurs(demandeurs.filter(demandeur => demandeur.id !== id));
    } catch (err) {
      console.error('Erreur API:', err);
      setError('Erreur lors de la suppression du demandeur');
    }
  };

  // Fonction pour mettre à jour un demandeur
  const updateDemandeur = async () => {
    try {
      const response = await axios.put(`http://localhost:8080/api/demandeurs/${editingDemandeur.id}`, editingDemandeur);
      setDemandeurs(demandeurs.map(demandeur => demandeur.id === editingDemandeur.id ? response.data : demandeur));
      setEditingDemandeur(null);
    } catch (err) {
      console.error('Erreur API:', err);
      setError('Erreur lors de la modification du demandeur');
    }
  };

  // Fonction d'impression
  const printList = () => {
    window.print();
  };

  useEffect(() => {
    fetchDemandeurs();
  }, []);

  if (loading) return <p>Chargement...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="container">
      <h1>Liste des Demandeurs</h1>
    
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {demandeurs.length > 0 ? (
            demandeurs.map(demandeur => (
              <tr key={demandeur.id}>
                <td>{demandeur.id}</td>
                <td>{demandeur.username}</td>
                <td>{demandeur.email}</td>
                <td>
                  <button onClick={() => setEditingDemandeur(demandeur)}>Modifier</button>
                  <button onClick={() => deleteDemandeur(demandeur.id)}>Supprimer</button>
                  
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">Aucun demandeur trouvé.</td>
            </tr>
          )}
        </tbody>
      </table>
      <button onClick={printList} className="print-button">Imprimer</button>

      <div className="form-container">
        <h2>{editingDemandeur ? 'Modifier Demandeur' : 'Ajouter Demandeur'}</h2>
        <input
          type="text"
          placeholder="Username"
          value={editingDemandeur ? editingDemandeur.username : newDemandeur.username}
          onChange={(e) => (editingDemandeur ? setEditingDemandeur({ ...editingDemandeur, username: e.target.value }) : setNewDemandeur({ ...newDemandeur, username: e.target.value }))}
        />
        <input
          type="email"
          placeholder="Email"
          value={editingDemandeur ? editingDemandeur.email : newDemandeur.email}
          onChange={(e) => (editingDemandeur ? setEditingDemandeur({ ...editingDemandeur, email: e.target.value }) : setNewDemandeur({ ...newDemandeur, email: e.target.value }))}
        />
        <button onClick={editingDemandeur ? updateDemandeur : addDemandeur}>
          {editingDemandeur ? 'Mettre à jour' : 'Ajouter'}
        </button>
        {editingDemandeur && <button className="cancel-button" onClick={() => setEditingDemandeur(null)}>Annuler</button>}
      </div>
    </div>
  );
};

export default ListeDemandeurs;
