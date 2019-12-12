//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package API.Controllers;

import API.Helpers.PdfGeneratorHelper;
import API.Services.AssignmentService.IAssignmentCommentService;
import API.Services.AssignmentService.IAssignmentService;
import Shared.ForCreation.AssignmentCommentForCreationDto;
import Shared.ForCreation.AssignmentCommentForUpdateDto;
import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/assignments"})
public class AssignmentController {
    private IAssignmentService assignmentService;
    private IAssignmentCommentService assignmentCommentService;
    private PdfGeneratorHelper p;

    public AssignmentController() {
    }

    @Autowired
    public void setP(PdfGeneratorHelper p) {
        this.p = p;
    }

    @Autowired
    public void setAssignmentCommentService(IAssignmentCommentService assignmentCommentService) {
        this.assignmentCommentService = assignmentCommentService;
    }

    @Autowired
    public void setAssignmentService(IAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @RequestMapping(
            method = {RequestMethod.GET}
    )
    public ResponseEntity<?> listAssignments(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "ASC") Direction sortDirection) {
        return new ResponseEntity(this.assignmentService.listAssignmentsPage(PageRequest.of(pageNumber, pageSize, sortDirection, new String[]{sortBy})), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity<?> findAssignment(@PathVariable int id) {
        return new ResponseEntity(this.assignmentService.findAssignment(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/date/{date}"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity<?> listAssignmentsDate(@PathVariable @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd") Date date) {
        return new ResponseEntity(this.assignmentService.listAssignmentsDate(date), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST}
    )
    public ResponseEntity<?> addAssignment(@RequestBody AssignmentForCreationDto assignment) {
        return new ResponseEntity(this.assignmentService.addAssignment(assignment), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    public ResponseEntity<?> deleteAssignment(@PathVariable int id) {
        return new ResponseEntity(this.assignmentService.deleteAssignment(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/"},
            method = {RequestMethod.PATCH}
    )
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentForUpdateDto forUpdate) {
        return new ResponseEntity(this.assignmentService.updateAssignment(forUpdate), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/{id}/comment/"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity<?> listServiceUserComments(@PathVariable int id) {
        return new ResponseEntity(this.assignmentCommentService.listAssignmentComments(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/comment/{commentID}"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity<?> findServiceUserComment(@PathVariable int commentID) {
        return new ResponseEntity(this.assignmentCommentService.findAssignmentComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/comment/{commentID}"},
            method = {RequestMethod.DELETE}
    )
    public ResponseEntity<?> deleteServiceUserComment(@PathVariable int commentID) {
        return new ResponseEntity(this.assignmentCommentService.deleteAssignmentComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/{id}/comment/"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<?> addServiceUserComment(@PathVariable int id, @RequestBody @Valid AssignmentCommentForCreationDto comment) {
        comment.setAssignmentId(id);
        return new ResponseEntity(this.assignmentCommentService.addAssignmentComment(comment), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/{id}/comment/"},
            method = {RequestMethod.PATCH}
    )
    public ResponseEntity<?> updateServiceUserComment(@PathVariable int id, @RequestBody @Valid AssignmentCommentForUpdateDto comment) {
        comment.setAssignmentId(id);
        return new ResponseEntity(this.assignmentCommentService.updateAssignmentComment(comment), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/{id}/raport/"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<?> generateRaport(@PathVariable int id) throws IOException, DocumentException {
        this.p.generatePDFFromHTML(id);
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }
}
