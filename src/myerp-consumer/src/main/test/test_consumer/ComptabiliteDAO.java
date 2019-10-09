package test_consumer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;

public class ComptabiliteDAO {

	List<EcritureComptable> listeEcritureComptable = new ArrayList<>();
	List<JournalComptable> listeJournalComptable = new ArrayList<>();
	List<CompteComptable> listeCompteComptable = new ArrayList<>();

	Connection connection;
	Statement statement;
	ResultSet result;

	// AnnotationConfigApplicationContext context = new
	// AnnotationConfigApplicationContext(AppConfig.class);

	// LivreService livreService = context.getBean(LivreService.class);
	// ReservationService reservationService =
	// context.getBean(ReservationService.class);
	// UtilisateurService utilisateurService =
	// context.getBean(UtilisateurService.class);
	// PretService pretService = context.getBean(PretService.class);

	public List<EcritureComptable> getListeEcritureComptable() throws IOException {

		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibliotheque");

			statement = connection.createStatement();
			result = statement.executeQuery("select * from ecriture_comptable");

			while (result.next()) {

				EcritureComptable ec = new EcritureComptable();

				String journal_comptable = result.getString(2);
				String reference = result.getString(3);
				Date date = result.getDate(4);
				String libelle = result.getString(5);

				ec.setDate(date);
				ec.setJournal_code(journal_comptable);
				ec.setLibelle(libelle);
				ec.setReference(reference);

				listeEcritureComptable.add(ec);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listeEcritureComptable;
	}

	public void Inserer(EcritureComptable ecritureComptable) throws IOException {

		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibliotheque");

			statement = connection.createStatement();

			String journal_code = ecritureComptable.getJournal_code();
			String reference = ecritureComptable.getReference();
			Date date = ecritureComptable.getDate();
			String libelle = ecritureComptable.getLibelle();

			String sql = "insert into ecriture_comptable (journal_code, reference, date, libelle) values ( '"
					+ journal_code + "', '" + reference + "' ,'" + date + "', '" + libelle + "')";
			result = statement.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void supprimerEcritureComptable(int id) throws IOException{

		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibliotheque");

			statement = connection.createStatement();
			
			String sql = "delete from ecriture_comptable where id = '" + id + "'";
			statement.executeQuery(sql);	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<JournalComptable> getListeJournalComptable() throws IOException {
		
		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibliotheque");

			statement = connection.createStatement();
			result = statement.executeQuery("select * from journal_comptable");

			while (result.next()) {

				JournalComptable jc = new JournalComptable();

				String code = result.getString(1);
				String libelle = result.getString(2);
				
				jc.setCode(code);
				jc.setLibelle(libelle);

				listeJournalComptable.add(jc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listeJournalComptable;
	}
	
public List<CompteComptable> getListeJCompteComptable() throws IOException {
		
		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibliotheque");

			statement = connection.createStatement();
			result = statement.executeQuery("select * from compte_comptable");

			while (result.next()) {

				CompteComptable cp = new CompteComptable();

				int numero = result.getInt(1);
				String libelle = result.getString(2);
				
				cp.setNumero(numero);
				cp.setLibelle(libelle);

				listeCompteComptable.add(cp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listeCompteComptable;
	}

}
