package src.Smartphone;

/**
 * Variables
 * Attribution et Appel des variables vitaux de la galerie et des contacts
 */
public class Variables
{
	private String name, lastname, telephone, portable, email, profession, organisation, web, dateNaissance, song, nickname, pic, favoris;

	/**
	 * @param : Nom du contact / de l'image
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param : Nom du contact / de l'image
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param : Nom de famille du contact
	 */
	public String getLastname()
	{
		return lastname;
	}

	/**
	 * @param : Nom de famille du contact
	 */
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	/**
	 * @param : Numéro de téléphone fixe du contact
	 */
	public String getTelephone()
	{
		return telephone;
	}

	/**
	 * @param : Numéro de téléphone fixe du contact
	 */
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	/**
	 * @param : Numéro de téléphone portable du contact
	 */
	public String getPortable() {
		return portable;
	}

	/**
	 * @param : Numéro de téléphone portable du contact
	 */
	public void setPortable(String portable)
	{
		this.portable = portable;
	}

	/**
	 * @param : Adresse email du contact
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param : Adresse email du contact
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @param : Profession du contact
	 */
	public String getProfession()
	{
		return profession;
	}

	/**
	 * @param : Profession du contact
	 */
	public void setProfession(String profession)
	{
		this.profession = profession;
	}

	/**
	 * @param : Organisation sociale / titre du contact
	 */
	public String getOrganisation() {
		return organisation;
	}

	/**
	 * @param : Organisation sociale / titre du contact
	 */
	public void setOrganisation(String organisation)
	{
		this.organisation = organisation;
	}

	/**
	 * @param : Site web du contact
	 */
	public String getWeb()
	{
		return web;
	}

	/**
	 * @param : Site web du contact
	 */
	public void setWeb(String web)
	{
		this.web = web;
	}

	/**
	 * @param : Date de naissance du contact
	 */
	public String getDateNaissance()
	{
		return dateNaissance;
	}

	/**
	 * @param : Date de naissance du contact
	 */
	public void setDateNaissance(String dateNaissance)
	{
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @param : Chanson personnalisée du contact
	 */
	public String getSong()
	{
		return song;
	}

	/**
	 * @param : Chanson personnalisée du contact
	 */
	public void setSong(String song)
	{
		this.song = song;
	}
	
	/**
	 * @param : Surnom du contact
	 */
	public String getNickname()
	{
		return nickname;
	}

	/**
	 * @param : Surnom du contact
	 */
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	/**
	 * @param : Chemin absolu de l'image du contact
	 */
	public String getPic()
	{
		return pic;
	}

	/**
	 * @param : Chemin absolu de l'image du contact
	 */
	public void setPic(String pic)
	{
		this.pic = pic;
	}

	/**
	 * @param : Si favori = true / si non favori = false
	 */
	public String getFavoris()
	{
		return favoris;
	}

	/**
	 * @param : Si favori = true / si non favori = false
	 */
	public void setFavoris(String favoris)
	{
		this.favoris = favoris;
	}
	
	
}
