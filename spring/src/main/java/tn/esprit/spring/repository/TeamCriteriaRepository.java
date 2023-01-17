package tn.esprit.spring.repository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TeamCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public TeamCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Team> findAllWithFilters(TeamPage teamPage,
                                             TeamSearchCriteria teamSearchCriteria){
        CriteriaQuery<Team> criteriaQuery = criteriaBuilder.createQuery(Team.class);
        Root<Team> teamRoot = criteriaQuery.from(Team.class);
        Predicate predicate = getPredicate(teamSearchCriteria, teamRoot);
        criteriaQuery.where(predicate);
        setOrder(teamPage, criteriaQuery, teamRoot);

        TypedQuery<Team> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(teamPage.getPageNumber() * teamPage.getPageSize());
        typedQuery.setMaxResults(teamPage.getPageSize());

        Pageable pageable = getPageable(teamPage);

        long teamsCount = getTeamsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, teamsCount);
    }

    private Predicate getPredicate(TeamSearchCriteria teamSearchCriteria,
                                   Root<Team> teamRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(teamSearchCriteria.getTeamName())){
            predicates.add(
                    criteriaBuilder.like(teamRoot.get("teamName"),
                            "%" + teamSearchCriteria.getTeamName() + "%")
            );
        }
        if(Objects.nonNull(teamSearchCriteria.getPoints())){
            predicates.add(
                    criteriaBuilder.like(teamRoot.get("points"),
                            "%" + teamSearchCriteria.getPoints() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(TeamPage teamPage,
                          CriteriaQuery<Team> criteriaQuery,
                          Root<Team> teamRoot) {
        if(teamPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(teamRoot.get(teamPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(teamRoot.get(teamPage.getSortBy())));
        }
    }

    private Pageable getPageable(TeamPage teamPage) {
        Sort sort = Sort.by(teamPage.getSortDirection(), teamPage.getSortBy());
        return PageRequest.of(teamPage.getPageNumber(),teamPage.getPageSize(), sort);
    }

    private long getTeamsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Team> countRoot = countQuery.from(Team.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
