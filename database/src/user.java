public class user 
{
		protected String password;
	 	protected String email;
	    protected String firstName;
	    protected String lastName;
	    protected String address_street_num;
	    protected String address_street;
	    protected String address_city;
	    protected String address_state;
	    protected String address_zip_code;
	    protected String birthday;
	    protected int cash_bal;
	    protected int PPS_bal;
	 
	    //constructors
	    public user() {
	    }
	 
	    public user(String email) 
	    {
	        this.email = email;
	    }
	    
	    public user(String email, String firstName, String lastName) 
	    {
	        this.email = email;
	        this.firstName = firstName;
	        this.lastName = lastName;
	    }
	    
	    public user(String email,String firstName, String lastName, String password,String birthday, String address_street_num, String address_street, String address_city, String address_state,String address_zip_code, int cash_bal,  int PPS_bal) 
	    {
	    	this(firstName,lastName,password,birthday, address_street_num,  address_street,  address_city,  address_state,  address_zip_code,cash_bal,PPS_bal);
	    	this.email = email;
	    }
	 
	
	    public user(String firstName, String lastName, String password,String birthday, String address_street_num, String address_street, String address_city, String address_state,String address_zip_code, int cash_bal,  int PPS_bal) 
	    {
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.password = password;
	        this.birthday = birthday;
	        this.address_street_num = address_street_num;
	        this.address_street = address_street;
	        this.address_city= address_city;
	        this.address_state = address_state;
	        this.address_zip_code = address_zip_code;
	        this.cash_bal = cash_bal;
	        this.PPS_bal = PPS_bal;
	    }
	    
	   //getter and setter methods
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	  
	    public String getBirthday() {
	    	return birthday;
	    }
	    public void setBirthday(String birthday) {
	    	this.birthday = birthday;
	    }
	    
	    public String getaddress_street_num() {
	        return address_street_num;
	    }
	    public void setaddress_street_num(String address_street_num) {
	        this.address_street_num = address_street_num;
	    }
	    public String getaddress_street() {
	        return address_street;
	    }
	    public void setaddress_street(String address_street) {
	        this.address_street = address_street;
	    }
	    public String getaddress_city() {
	        return address_city;
	    }
	    public void setaddress_city(String address_city) {
	        this.address_city = address_city;
	    }
	    public String getaddress_state() {
	        return address_state;
	    }
	    public void setaddress_state(String address_state) {
	        this.address_state = address_state;
	    }
	    public String getaddress_zip_code() {
	        return address_zip_code;
	    }
	    public void setaddress_zip_code(String address_zip_code) {
	        this.address_zip_code = address_zip_code;
	    }
	    
	    public int getCash_bal() {
	    	return cash_bal;
	    }
	    public void setCash_bal(int cash_bal) {
	    	this.cash_bal = cash_bal;
	    }
	    
	    public int getPPS_bal() {
	    	return PPS_bal;
	    }
	    public void setPPS_bal(int PPS_bal) {
	    	this.PPS_bal = PPS_bal;
	    }
	}
