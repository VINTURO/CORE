/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vinturo.core.exceptions.UsernameIsAlreadyRegistredException;
import org.vinturo.core.storage.dao.UserDAO;
import org.vinturo.core.storage.dao.impl.UserDAOImpl;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.entity.user.UserRegistration;
import org.vinturo.core.storage.service.UserService;
import org.vinturo.core.util.PasswordUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDAO dao;

    @Inject
    public UserServiceImpl(final UserDAOImpl dao) {
        this.dao = dao;
    }

    @Override
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public User createUser(UserRegistration registration) throws UsernameIsAlreadyRegistredException {

        // Check username is unique
        if (this.isUsernameAlreadyRegistred(registration.getUsername())) {
            throw new UsernameIsAlreadyRegistredException();
        }

        String encodedPassword = PasswordUtil.encodePassword(registration.getPlainPassword());

        User usr = new User(registration.getUsername(), encodedPassword);
        usr.setApproved(false);

        //   usr.addRole(Role.ROLE_USER);

        EntityManager em = dao.getEntityManager().get();

        em.persist(usr);
        em.flush();

        return usr;
    }

    @Override
    public boolean isUsernameAlreadyRegistred(String username) {

        User found = dao.findByUsername(username);
        if (found == null) {
            return false;
        } else {
            return true;
        }
    }

//
//    @Override
//    public User createUser(
//            ApprovedUserRegistration registration) throws UsernameIsAlreadyRegistredException, GroupNotFoundException {
//
//        // Check username is unique
//        if (this.isUsernameAlreadyRegistred(registration.getUsername())) {
//            throw new UsernameIsAlreadyRegistredException();
//        }
//
//        String encodedPassword = PasswordUtil.encodePassword(registration.getPlainPassword());
//
//        EntityManager em = dao.getEntityManager().get();
//        em.getTransaction().begin();
//
//        User usr = new User(registration.getUsername(), encodedPassword);
//        usr.setApproved(true);
//
//        Group group = groupService.findById(registration.getGroupId());
//        if (group == null) {
//            throw new GroupNotFoundException(String.format("Unable to find any group with id = %s", registration.getGroupId()));
//        }
//
//        usr.setGroup(group);
//
//        em.persist(usr);
//
//        em.getTransaction().commit();
//
//        return usr;
//    }
//
//    @Override
//    @Transactional
//    public User approveUser(@NotNull String username) throws UserNotFoundException, UserIsAlreadyApprovedException {
//
//        User usr = this.findUserByUsername(username);
//
//        if (usr.isApproved()) {
//            throw new UserIsAlreadyApprovedException();
//        }
//
//        usr.setApproved(true);
//        return usr;
//
//    }
//
//    @Override
//    public User findUserByUsername(String username) throws UserNotFoundException {
//
//        try {
//            EntityManager em = dao.getEntityManager().get();
//            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username);
//            User found = query.setMaxResults(1).getSingleResult();
//            return found;
//        } catch (NoResultException ex) {
//            throw new UserNotFoundException();
//        }
//
//    }

//
//    @Override
//    @Transactional
//    public Consumer authenticate(
//            @Valid VinturoAuth auth) throws InvalidCreditentialsException, AccountNotValidatedException {
//
//        try {
//            User usr = findUserByUsername(auth.getUsername());
//
//            if (!PasswordUtil.checkPassword(auth.getPassword(), usr.getPassword())) {
//                throw new InvalidCreditentialsException();
//            }
//
//            if (!usr.isApproved()) {
//                throw new AccountNotValidatedException();
//            }
//
//            // Username and password are correct !
//
//            // Try to create a new consumer with an apikey !
//
//            // TODO persist if not already existing
//            Consumer consumer = null;
//
//            consumer = new Consumer(auth.getDevice().getName(),
//                    auth.getDevice().getType(),
//                    auth.getDevice().getOS(),
//                    auth.getDevice().getUID());
//
//            try {
//                usr.addConsumer(consumer);
//                EntityManager em = dao.getEntityManager().get();
//                em.flush();
//            } catch (ConsumerAlreadyLinkedWithUserException e) {
//
//                try {
//                    consumer = this.getConsumerWithUID(auth.getDevice().getUID());
//                } catch (ConsumerNotFoundException ex) {
//                    logger.error(ex.getMessage());
//                }
//
//            }
//            return consumer;
//
//        } catch (UserNotFoundException e) {
//            throw new InvalidCreditentialsException();
//        }
//
//    }
//
//    private Consumer getConsumerWithUID(String uid) throws ConsumerNotFoundException {
//
//        try {
//            EntityManager em = dao.getEntityManager().get();
//            TypedQuery<Consumer> query = em.createNamedQuery("Consumer.findByUID", Consumer.class).setParameter("UID", uid);
//            Consumer found = query.setMaxResults(1).getSingleResult();
//            return found;
//        } catch (NoResultException ex) {
//            throw new ConsumerNotFoundException();
//        }
//
//    }
//
//    /**
//     * @param apiKey
//     * @return
//     */
//    @Override
//    public Consumer getConsumerWithAPIKey(String apiKey) throws ConsumerNotFoundException {
//
//        try {
//            EntityManager em = dao.getEntityManager().get();
//            TypedQuery<Consumer> query = em.createNamedQuery("Consumer.findByAPIKey", Consumer.class).setParameter("key", apiKey);
//            Consumer found = query.setMaxResults(1).getSingleResult();
//            return found;
//        } catch (NoResultException ex) {
//            throw new ConsumerNotFoundException();
//        }
//
//    }

}