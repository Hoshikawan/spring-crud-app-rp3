package jp.co.sss.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sss.crud.entity.Department;

@Mapper
public interface DepartmentMapper {

	/**	
	 * 部署検索
	 * 
	 * @param deptId
	 * @return 部署エンティティ
	 */
	Department findByDeptId(@Param(value = "deptId") Integer deptId);

}
