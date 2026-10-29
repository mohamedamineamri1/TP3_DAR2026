# TP 3  — Serveur Multi-threads (sockets TCP)
Matière : Développement d'applications réparties Auditoire : LSI 3 — Faculté des Sciences de Sfax — 2026/2027 

Enseignante des TPs : Mme Zeineb DAOUD 

Étudiant : Mohamed Amine AMRI

# Sommaire 

Activité 3-1 : acceptation de plusieurs clients 

Activité 3-2 : calculatrice multi-thread  

# Objectif  
Dans les TP1 et TP2, le serveur ne pouvait traiter qu'un seul client par exécution. 
Ce TP transforme le squelette Client/Serveur en un véritable serveur multi-thread capable de :  

      accepter plusieurs clients simultanément (un thread dédié par client)  
      
      identifier chaque client (adresse IP + numéro d'ordre de connexion)  
      
      exposer un service de calculatrice par échange d'objets sérialisés  
      
      protéger une ressource critique (compteur global d'opérations) contre les accès concurrents. 

# Activité 3-1 : acceptation de plusieurs clients 

3.1.1  Principe

Le thread principal ne fait qu'une chose : boucler sur accept(). À chaque connexion il : 

        incrémente le numéro d'ordre du client  
        
        affiche son adresse IP via socket.getRemoteSocketAddress()  
        
        lance un ClientProcess  puis retourne immédiatement à accept(). 
  Résultat des tests locaux 
  
  <img width="535" height="117" alt="5" src="https://github.com/user-attachments/assets/041f0d06-e275-4692-8df4-f8522106f3ef" />  

  <img width="822" height="322" alt="6" src="https://github.com/user-attachments/assets/1ccfaa25-dd6e-4de6-910f-7083d10ae210" /> 

  # Activité 3-2 : calculatrice multi-thread  
  3.2.1  Principe  
  
  Le service de calculatrice du TP2 est intégré au serveur multi-thread.  
  Client et serveur échangent des objets Operation  qui transportent à la fois la requête (opérandes,opérateur)  
  et la réponse (résultat,erreur,compteur global).  
  
  Résultat des tests locaux 
  <img width="743" height="372" alt="7" src="https://github.com/user-attachments/assets/7b148497-b075-47b1-82a5-39600071295b" />  

 # Conclusion 
 

Ce TP a permis de passer d'un serveur séquentiel à un serveur concurrent basé sur le modèle « un thread par client », d'échanger des objets sur TCP 
et de protéger une ressource partagée par synchronisation.
  

  

