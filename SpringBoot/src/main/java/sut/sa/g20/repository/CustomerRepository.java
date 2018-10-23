package sut.sa.g20.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sut.sa.g20.entity.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public
interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
          CustomerEntity findBycustomername(String customername);
          CustomerEntity findBycustomeraddress(String customeraddress);
          CustomerEntity findBycustomeremail(String customeremail);
          CustomerEntity findBycustomerphone(String customerphone);
          CustomerEntity findBycustomerpass(String customerpass);
          CustomerEntity findBycustomerid(Long customerid);
}
