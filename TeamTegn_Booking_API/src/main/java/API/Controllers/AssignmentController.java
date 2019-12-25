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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Date;

/**
 * The controller hold responsibility of listing all assignments in pages, displaying the assignments for a specific data,
 * finding, deleting, creating and updating assignment as well as managing assignment comments.
 */
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private IAssignmentService assignmentService;
    private IAssignmentCommentService assignmentCommentService;
    private PdfGeneratorHelper pdfGeneratorHelper;

    public AssignmentController() {
    }

    @Autowired
    public void setPdfGeneratorHelper(PdfGeneratorHelper pdfGeneratorHelper) {
        this.pdfGeneratorHelper = pdfGeneratorHelper;
    }

    @Autowired
    public void setAssignmentCommentService(IAssignmentCommentService assignmentCommentService) {
        this.assignmentCommentService = assignmentCommentService;
    }

    @Autowired
    public void setAssignmentService(IAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    /**
     * GET request method that is responsible for retrieving a page of assignmentDto objects.
     * @param pageNumber <String> defines what page should be shown,
     * @param pageSize <String> defines page size,
     * @param sortBy <String> defines by witch field should the page be sorted,
     * @param sortDirection <String> defines whether sort ascending or descending
     * @return If successfully, it returns a code 302 (FOUND response) with am pageable object with assignments
     * listed in it.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentsPage(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "ASC") Direction sortDirection) {
        return new ResponseEntity(this.assignmentService.listAssignmentsPage(PageRequest.of(pageNumber, pageSize, sortDirection, new String[]{sortBy})), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that is responsible for finding an assignment from a database.
     * @param id <Integer> An unique identifier of a record from a database.
     * @return If successfully, it returns a code 302 (FOUND response) together with a found assignmentDto object with
     * filled information from a database. Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignment(@PathVariable int id) {
        return new ResponseEntity(this.assignmentService.findAssignment(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that retrieves list of assignments for a specific date.
     * @param date <Date> Value defining filtering the assignments with the same date.
     * @return If successfully, it returns a code 302 (FOUND response) together with a list of filtered assignments for
     * a specified date. Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentsDate(@PathVariable @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd") Date date) {
        return new ResponseEntity(this.assignmentService.listAssignmentsDate(date), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method, that is responsible of adding new Assignment to a database.
     * @param assignment <AssignmentForCreationDto> An object that need to be passed in order to complete the operation.
     *                   Required fields of the object:
     *                   destinationStreet <String> (Name of the destination street)
     *                   destinationCity <String> (Name of the destination city)
     *                   destinationPostCode <String> (Destination post code number)
     *                   destinationCountry <String> (Name of the destination country)
     *                   assignmentDate <Date> (Date of the assignment)
     *                   assignmentEndDate <Date> (Date when the assignment finishes)
     *                   startTime <Timestamp> (Start hour of the assignment)
     *                   endTime <Timestamp> (Finish hour of the assignment)
     *                   assignedStatus <Boolean> (Status of the assignment)
     *                   assignmentTypeId <Integer> (Id of the related assignment type)
     *                   assignmentImportanceId <Integer> (Id of the related assignment importance)
     *                   assignmentInterpretationTypeId <Integer> (Id of the related assignment interpretation type)
     *                   assignmentTitle <String> (Id of the related assignment title)
     *                   serviceUserId <Integer> (Id of the assignment's service user)
     *                   accountId <Integer> (Id of the assignment's account id)
     *                   assignmentStatusId <Integer> (Id of the related assignment status)
     *                   vocalLanguageId <Integer> (Id of the assignment's vocal language)
     *                   assignmentStatusTypeId <Integer> (Id of the related assignment status type)
     * @return If successfully, the method returns code 201 (CREATED response) together with AssignmentDto object with
     * filled fields that were passed while creation. Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addAssignment(@RequestBody AssignmentForCreationDto assignment) {
        return new ResponseEntity(this.assignmentService.addAssignment(assignment), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes assignment from a database. In fact the record is not deleted but its value is
     * changed.
     * @param id <Integer> Unique identifier of a record from a database that is used to delete the record.
     * @return IF successfully, it returns code 200 (OK response) and true value. Otherwise it returns false value or
     * error message with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAssignment(@PathVariable int id) {
        return new ResponseEntity(this.assignmentService.deleteAssignment(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates a record in a database.
     * @param forUpdate An object that need to be passed to complete the operation. Required object's fields:
     *                  id <Integer> (Unique identifier of the record in a database)
     *                  destinationStreet <String> (Name of the destination street)
     *                  destinationCity <String> (Name of the destination city)
     *                  destinationPostCode <String> (Destination post code number)
     *                  destinationCountry <String> (Name of the destination country)
     *                  assignmentDate <Date> (Date of the assignment)
     *                  assignmentEndDate <Date> (Date when the assignment finishes)
     *                  startTime <Timestamp> (Start hour of the assignment)
     *                  endTime <Timestamp> (Finish hour of the assignment)
     *                  assignedStatus <Boolean> (Status of the assignment)
     *                  assignmentTypeId <Integer> (Id of the related assignment type)
     *                  assignmentImportanceId <Integer> (Id of the related assignment importance)
     *                  assignmentInterpretationTypeId <Integer> (Id of the related assignment interpretation type)
     *                  assignmentTitle <String> (Id of the related assignment title)
     *                  serviceUserId <Integer> (Id of the assignment's service user)
     *                  accountId <Integer> (Id of the assignment's account id)
     *                  assignmentStatusId <Integer> (Id of the related assignment status)
     *                  vocalLanguageId <Integer> (Id of the assignment's vocal language)
     *                  assignmentStatusTypeId <Integer> (Id of the related assignment status type)
     * @return If successfully, it returns code 200 (OK response) together with an AssignmentDto object with updated
     * fields. If the field was not changed, the old one should be appear. Otherwise it returns error message with
     * appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentForUpdateDto forUpdate) {
        return new ResponseEntity(this.assignmentService.updateAssignment(forUpdate), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET request method that retrieves all comments of an assignment.
     * @param id <Integer> Unique identifier of the assignment from a database.
     * @return If successfully, it returns code 302 (FOUND response) together with a list of AssignmentCommentDto objects.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentComments(@PathVariable int id) {
        return new ResponseEntity(this.assignmentCommentService.listAssignmentComments(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds an assignment's comment
     * @param commentID <Integer> Unique identifier of a comment record from a database.</Integer>
     * @return If successfully, it returns code 302 (FOUND response) and the found AssignmentCommentDto object with all
     * information filled. Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentComment(@PathVariable int commentID) {
        return new ResponseEntity(this.assignmentCommentService.findAssignmentComment(commentID), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * DELETE request method that removes a comment from a database.
     * @param commentID <Integer> Unique identifier of a assignment's comment.
     * @return If successfully, it returns code 200 (OK response) and the true value. Otherwise it returns false value
     * or error message with appreciate message.
     */
    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAssignmentComment(@PathVariable int commentID) {
        return new ResponseEntity(this.assignmentCommentService.deleteAssignmentComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * POST request method that adds comment to a specified assignment.
     * @param id <Integer> Unique identifier of an assignment to which a cimment is beign updaed
     * @param comment <AssignmentCommentForCreationDto> object that is required to finish the operation. Required object's
     *                fields:
     *                ~ commentText <String> (Text of the comment)
     * @return If successfully, it returns code 201 (CREATED response) together with a AssignmentCommentDto object with
     * all information field that were added to a database. Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<?> addAssignmentComment(@PathVariable int id, @RequestBody @Valid AssignmentCommentForCreationDto comment) {
        comment.setAssignmentId(id);
        return new ResponseEntity(this.assignmentCommentService.addAssignmentComment(comment), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * PATCH request method that is responsible for updating a record in a database.
     * @param id <Integer> Unique identifier of an assignment to which a comment is being added.
     * @param comment <AssignmentCommentForUpdateDto> object that is required to finish the operation. Required objects'
     *                fields:
     *                ~ id <Integer> (Unique identifier of the comment record from a database)
     *                ~ commentText <String> (Text of the comment)
     * @return If successfully, it returns code 200 (OK response). Additionally a assignmentCommentDto object is being
     * returned with new information that is filled. Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAssignmentComment(@PathVariable int id, @RequestBody @Valid AssignmentCommentForUpdateDto comment) {
        comment.setAssignmentId(id);
        return new ResponseEntity(this.assignmentCommentService.updateAssignmentComment(comment), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * POST request method that generates a report for an assignment, that will be signed by service providers and
     * service users after the work is done.
     * @param id <Integer> Unique identifier of the assignment for which the report should be created.
     * @return If successfully, code 200 (OK response) and the report is generated. Otherwise it returns error message with
     * appreciate message.
     * @throws IOException Is thrown while generating the report.
     */
    @RequestMapping(value = "/{id}/report/", method = {RequestMethod.POST})
    public ResponseEntity<?> generateReport(@PathVariable int id) throws IOException {
        this.pdfGeneratorHelper.generatePDFFromHTML(id);
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }
}
