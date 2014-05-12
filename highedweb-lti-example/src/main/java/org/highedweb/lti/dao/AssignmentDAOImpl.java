package org.highedweb.lti.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.highedweb.lti.domain.Assignment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AssignmentDAOImpl implements AssignmentDAO{
	
	private static final long serialVersionUID = 1L;

    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
    	//System.out.println("UserRolesDAOHib setSessionFactory");
        this.sessionFactory = sessionFactory;
    }	

	@Override
	public Assignment addAssignment(Assignment assignment) {
	
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(assignment);
		return assignment;
	}

	@Override
	public Assignment updateAssignment(Assignment assignment) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(assignment);
		return assignment;
	}

	@Override
	public Assignment getAssignmentByContextAndUserId(String contextId,
			String userID) {
		Session session = sessionFactory.getCurrentSession();		
		
        @SuppressWarnings("unchecked")
        List<Assignment> assignmentList = session.createQuery("from Assignment where contextId = ? and userId = ?").setString(0, contextId).setString(1, userID).list();
        
        if(assignmentList.size() == 1)
        	return assignmentList.get(0);
        else 
        	return null;
	}

	@Override
	public List<Assignment> getAssignmentsByContext(String contextId) {
		Session session = sessionFactory.getCurrentSession();
		
        List<Assignment> assignmentList = session.createQuery("from Assignment where contextId = ?").setString(0, contextId).list();
        
        return assignmentList;

	}

}
