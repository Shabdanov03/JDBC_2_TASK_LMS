package org.example.service;

import org.example.models.Job;

import java.util.List;

/**
 * Shabdanov Ilim
 **/
public interface JobService {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
