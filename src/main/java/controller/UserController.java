/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import model.User;

@Stateless
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user)
    {
      entityManager.persist(user);
    }

    public List<User> all()
    { 
        CriteriaQuery<User> cq = entityManager.getCriteriaBuilder().createQuery(User.class);
        cq.select(cq.from(User.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
    public User byId(int id){
        return entityManager.find(User.class, id);
   }
   
    
    public List<User> adults()
    { 
    	String hql = "Select u from User u where u.age > :age";
		TypedQuery<User> q = entityManager.createQuery(hql,User.class);
		q.setParameter("age", 18);
        return q.getResultList();
    }
    
    public User getAuthUser(String username,String password){
		for(User user : all()){
			if (user.getUsername().equals(username) 
			 && user.getPassword().equals(password)){
					return user;
			}
		}
		return null;
	}
   
}
