package com.github.ting723.repository;

import com.github.ting723.entity.InterfaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : zhanglianwei
 * Create : 2018/3/27 17:53
 * Update : 2018/3/27 17:53
 * Descriptions :
 *
 * @author zhanglianwei
 */
public interface InterfaceRepository extends JpaRepository<InterfaceEntity, String> {
    /**
     * 通过projectId 查找所有httpntity
     *
     * @param projectId
     * @return
     */
    List<InterfaceEntity> findInterfaceEntityByProjectId(String projectId);
}
