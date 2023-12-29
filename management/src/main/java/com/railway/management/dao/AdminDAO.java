package com.railway.management.dao;

import com.railway.management.entity.Admin;

import java.util.List;

public interface AdminDAO {

    void saveAdmin(Admin admin);

    Admin getAdminById(int id);

    List<Admin> getAllAdmins();

    void updateAdmin(Admin admin);

    void deleteAdmin(int id);
}
