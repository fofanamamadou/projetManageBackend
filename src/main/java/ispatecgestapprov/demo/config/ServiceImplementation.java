/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispatecgestapprov.demo.config;

import org.springframework.beans.factory.annotation.Autowired;

import ispatecgestapprov.demo.repositories.utilisateurRepository;

/**
 *
 * @author PIERRE
 */
@org.springframework.stereotype.Service
public class ServiceImplementation implements Service{
    @Autowired
    protected utilisateurRepository utilisateurRepository;
    
}
