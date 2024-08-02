import React from 'react';
import './Homepage.css'; // Assurez-vous que le chemin est correct


const HomePage = () => {
  return (
    <div className="background">
      {/* Navbar */}
      <div className="navbar">
        <div className="logo">Gestion RH</div>
        <div className="nav-links">
          <a href="#home" >Home</a>
          <a href="#employee">Employée</a>
          <a href="#about-us">About Us</a>
        </div>
      </div>

      {/* Contenu de la page */}
      <h1 className="title">
        Bienvenue sur la page d'accueil
      </h1>
      <p className="description">
      <p>
            La gestion des ressources humaines (GRH) est un domaine crucial dans toute organisation, quelle que soit sa taille.</p>
            <p> Elle englobe l'ensemble des pratiques visant à attirer, développer, motiver et retenir les talents au sein de l'entreprise.</p>
           <p>  Une gestion efficace des RH contribue non seulement à améliorer la performance individuelle des employés mais également à 
             renforcer la performance globale de l'organisation.
        </p>
        <p>
            Les principales fonctions de la GRH incluent le recrutement et la sélection des employés, la formation et le développement,
             la gestion des performances, la planification des carrières, ainsi que la gestion des rémunérations et des avantages.
             Une stratégie RH bien définie permet de créer un environnement de travail positif et engageant,
              où les employés se sentent valorisés et motivés à contribuer à la réussite de l'entreprise.
        </p>
        <p>
            En outre, la gestion des ressources humaines joue un rôle clé dans la gestion du changement et l'adaptation aux évolutions du marché.
             Elle aide les organisations à naviguer dans un environnement en constante évolution,en s'assurant que les compétences et les talents 
             sont alignés avec les objectifs stratégiques de l'entreprise. En investissant dans la GRH,
            les entreprises peuvent non seulement améliorer leur efficacité opérationnelle mais aussi favoriser une culture d'innovation et de croissance continue.
        </p>
      </p>
      <a
        href="https://rivaindustries.ma/"
        className="external-link" 
      >
        la page de Riva Industries
      </a>
      <div id="footer"><p>copyright@2024</p></div>
    </div>
   
  );
};

export default HomePage;
