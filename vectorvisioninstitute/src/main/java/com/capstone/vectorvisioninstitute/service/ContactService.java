package com.capstone.vectorvisioninstitute.service;

import com.capstone.vectorvisioninstitute.constants.VectorVisionConstants;
import com.capstone.vectorvisioninstitute.model.Contact;
import com.capstone.vectorvisioninstitute.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 @RequestScope will reset the counter to zero everytime a user submits the form,
 it will create a new bean for each and every HTTP request that is coming from the end user.
 example: If there are 100 users trying to access your web application at the same time, and if they are submitting
 100 different contact message details with 100 different HTTP requests, then definitely they will get
 100 different ContactService beans inside your web application.
 */

//@Slf4j
@Service
//@RequestScope
//@ApplicationScope
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    //slf4j
    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    /**
     * Save Contact Details into DataBase
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(VectorVisionConstants.OPEN);
        /* manually populating createdBy and createdAt()
         * now since we have auditing class we don't need them anymore
        contact.setCreatedBy(VectorVisionConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());*/

        Contact savedContact = contactRepository.save(contact);
        /*if getContactId > 0 indicates that successful processing of the data
        and insertion into the database is completed*/
        if(null != savedContact && savedContact.getContactId() > 0){
            isSaved = true;
        }
        return isSaved;
    }

    //Derived Query Method in Repo
    public List<Contact> findMsgsWithOpenStatus(){
        //spring data JPA will insert some implementation at runtime
        List<Contact> contactMsgs = contactRepository.findByStatus(VectorVisionConstants.OPEN);
        return contactMsgs;
    }

    //old ver: public boolean updateMsgStatus(int contactId, String updatedBy)
    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        /*Optional since sometimes the primary key value may not exist
          inside our db, so we may get null*/
        Optional<Contact> contact = contactRepository.findById(contactId); //TODO: 1:39:00 11-2-23
        //.ifPresent checks if contact = null or != null
        contact.ifPresent(contact1 -> {
            contact1.setStatus(VectorVisionConstants.CLOSE); //set status to close
            /* Manually updating updatedBy() & updatedAt()
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(LocalDateTime.now());*/
        });
        //call.get() gets the actual object since Optional was used above
        Contact updatedContact = contactRepository.save(contact.get()); //update operation

        //Checking if the update was successful
        if(null != updatedContact && updatedContact.getUpdatedAt()!=null){
            isUpdated = true;
        }
        return isUpdated;
    }
}
