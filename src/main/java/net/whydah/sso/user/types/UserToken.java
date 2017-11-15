package net.whydah.sso.user.types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import net.whydah.sso.basehelpers.ValidationConfig;
import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.BaseExpires;
import net.whydah.sso.ddd.model.CellPhone;
import net.whydah.sso.ddd.model.Email;
import net.whydah.sso.ddd.model.FirstName;
import net.whydah.sso.ddd.model.Issuer;
import net.whydah.sso.ddd.model.LastName;
import net.whydah.sso.ddd.model.LastSeen;
import net.whydah.sso.ddd.model.Ns2link;
import net.whydah.sso.ddd.model.PersonRef;
import net.whydah.sso.ddd.model.SecurityLevel;
import net.whydah.sso.ddd.model.TimeStamp;
import net.whydah.sso.ddd.model.UID;
import net.whydah.sso.ddd.model.UserName;
import net.whydah.sso.ddd.model.UserTokenId;
import net.whydah.sso.ddd.model.UserTokenLifespan;
import net.whydah.sso.whydah.DEFCON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserToken implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(UserToken.class);
    private static String defcon;
    private UserTokenId usertokenid = new UserTokenId(UUID.randomUUID().toString());
    //From UIB
    private UID uid = new UID(UUID.randomUUID().toString());
    private PersonRef personRef;
    private UserName userName;
    private FirstName firstName;
    private LastName lastName;
    private Email email;
    private CellPhone cellPhone;
    private TimeStamp timestamp;
    private LastSeen lastSeen;
    private SecurityLevel securityLevel;
    private UserTokenLifespan lifespan = new UserTokenLifespan(BaseExpires.addPeriod(Calendar.MONTH, 6));
    private Issuer issuer;
    private Ns2link ns2link;
    private List<UserApplicationRoleEntry> roleList;

    public UserToken() {
        this.timestamp = new TimeStamp(Long.toString(System.currentTimeMillis()));
        this.lastSeen = new LastSeen();
        this.roleList = new LinkedList<>();
        if (UserToken.defcon == null || UserToken.defcon.length() < 1) {
            UserToken.defcon = DEFCON.DEFCON5.toString();
        }
    }

    public static String getDefcon() {
        if (UserToken.defcon == null || UserToken.defcon.length() < 1) {
            return DEFCON.DEFCON5.toString();
        }
        return UserToken.defcon;
    }

    public static void setDefcon(String defcon) {
        if (isInEnum(defcon, DEFCON.class)) {
            UserToken.defcon = defcon;

        } else if (UserToken.defcon == null || UserToken.defcon.length() < 1) {
            UserToken.defcon = DEFCON.DEFCON5.toString();
        }
    }

    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public String getLastSeen() {
        return lastSeen!=null?lastSeen.toString():null;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = new LastSeen(lastSeen);
    }

    public boolean isValid() {
    	
    	//This is to check useroken's life span
        if (timestamp == null || lifespan == null) {
            log.trace("usertoken invalid because timestamp or lifespan is not set. timestamp={}  lifespan={}", timestamp, lifespan);
            return false;
        }

        long now = System.currentTimeMillis();
        long timeout = timestamp.getValue() + lifespan.getMillisecondValue();
        boolean stillValid = timeout > now;
        if (!stillValid) {
            log.trace("usertoken invalid (timed out). timeout={} is NOT greater than now={}", timeout, now);
        }
        
        if (!stillValid) {
            log.trace("usertoken is invalid");
        } else {
        	log.trace("usertoken is valid!");
        }
        return stillValid;
    }

    public UserToken copy() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            byte[] obj = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(obj);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (UserToken) ois.readObject();
        } catch (Exception e) {
            log.error("Error copying UserToken", e);
        }
        return null;
    }

    //Used by usertoken.ftl
    public String getMD5() {
        String md5base = null2empty(getUid()) + null2empty(getPersonRef()) + null2empty(getUserTokenId()) + null2empty(getTimestamp())
                + null2empty(getFirstName()) + null2empty(getLastName()) + null2empty(getEmail()) + null2empty(getCellPhone()) + null2empty(getSecurityLevel()) + null2empty(getIssuer());
        log.trace("MD5base: " + md5base);
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(md5base.getBytes("UTF-8"));
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            return bigInt.toString(16);
        } catch (Exception e) {
            log.error("Error creating MD5 hash, returning empty string. userToken: " + toString(), e);
            return "";
        }
    }

    private String null2empty(String value) {
        return value != null ? value : "";
    }

    public void addApplicationRoleEntry(UserApplicationRoleEntry role) {
        roleList.add(role);
    }


    public String getUserTokenId() {
        return usertokenid!=null? usertokenid.getId():null;
    }

    public void setUserTokenId(String usertokenid) {

        this.usertokenid = new UserTokenId(usertokenid);
    }

    public String getUid() {
        return uid!=null?uid.getId():null;
    }

    public void setUid(String uid) {
        this.uid = new UID(uid);
    }

    public String getPersonRef() {
        return personRef!=null?personRef.getInput():null;
    }

    public void setPersonRef(String personRef) {
        this.personRef = new PersonRef(personRef);
    }

    public String getUserName() {
    	return userName!=null?userName.getInput():null;
    }

    public void setUserName(String userName) {
        this.userName = new UserName(userName);
    }

    public String getFirstName() {
        return firstName!=null? firstName.getInput():null;
    }

    public void setFirstName(String firstName) {
        this.firstName = new FirstName(firstName);
    }

    public String getLastName() {
        return lastName!=null? lastName.getInput():null;
    }

    public void setLastName(String lastName) {
        this.lastName = new LastName(lastName);
    }

    public String getEmail() {
        return email!=null?email.getInput():null;
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public String getCellPhone() {
        return cellPhone!=null?cellPhone.getInput():null;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = new CellPhone(cellPhone);
    }

    public String getTimestamp() {
        return timestamp!=null?timestamp.toString():null;
    }

    public String getTimestampFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(timestamp.toString(), 10)));
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = new TimeStamp(timestamp);
    }

    public String getSecurityLevel() {
        return securityLevel!=null?securityLevel.toString():null;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = new SecurityLevel(securityLevel);
    }

    public String getLifespan() {
        return lifespan!=null?Long.toString(lifespan.getMillisecondValue()):null;
    }


    public String getLifespanFormatted() {
        if (lifespan == null) {
            return "";
        }
        return lifespan.getDateFormatted();

    }



    public void setLifespan(String lifespan) {
        try {
            this.lifespan = new UserTokenLifespan(lifespan);
        } catch (Exception e) {

        }
    }

    // TODO  return a better issuer?
    public String getIssuer() {
        return issuer!=null?issuer.getInput():null;
    }

    public void setIssuer(String issuer) {
        this.issuer = new Issuer(issuer);
    }

    public List<UserApplicationRoleEntry> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserApplicationRoleEntry> roleList) {
        this.roleList = roleList;
    }

    public String getNs2link() {
        return ns2link!=null?ns2link.getInput():null;
    }

    public void setNs2link(String ns2link) {
        this.ns2link = new Ns2link(ns2link);
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "  usertokenid='" + getUserTokenId() + '\'' +
                ", uid='" + getUid() + '\'' +
                ", personRef='" + getPersonRef() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName()+ '\'' +
                ", email='" + getEmail() + '\'' +
                ", cellPhone='" + getCellPhone() + '\'' +
                ", timestamp='" + getTimestamp() + '\'' +
                ", lastSeen='" + getLastSeen() + '\'' +
                ", DEFCON='" + getDefcon() + '\'' +
                ", securityLevel='" + getSecurityLevel() + '\'' +
                ", lifespan='" + getLifespan() + '\'' +
                ", issuer='" + getIssuer() + '\'' +
                ", roleList.size=" + getRoleList().size() +
                ", MD5='" + getMD5() + '\'' +
                '}';
    }


    
    
}