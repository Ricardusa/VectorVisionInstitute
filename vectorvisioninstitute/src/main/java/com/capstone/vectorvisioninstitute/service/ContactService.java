package com.capstone.vectorvisioninstitute.service;

import com.capstone.vectorvisioninstitute.constants.VectorVisionConstants;
import com.capstone.vectorvisioninstitute.model.Contact;
import com.capstone.vectorvisioninstitute.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Contact savedContact = contactRepository.save(contact);
        /*if getContactId > 0 indicates that successful processing of the data
        and insertion into the database is completed*/
        if(null != savedContact && savedContact.getContactId() > 0){
            isSaved = true;
        }
        return isSaved;
    }

    //Pagination Configurations
    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(VectorVisionConstants.OPEN, pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        /*Optional since sometimes the primary key value may not exist
          inside our db, so we may get null*/
        Optional<Contact> contact = contactRepository.findById(contactId); //TODO: 1:39:00 11-2-23
        //.ifPresent checks if contact = null or != null
        contact.ifPresent(contact1 -> {
            contact1.setStatus(VectorVisionConstants.CLOSE); //set status to close
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
