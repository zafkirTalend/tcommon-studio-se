// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.ui.swt.proposal;

import java.io.File;

import org.eclipse.jdt.core.CompletionContext;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.eval.IEvaluationContext;
import org.eclipse.jdt.debug.eval.EvaluationManager;
import org.eclipse.jdt.debug.eval.IClassFileEvaluationEngine;
import org.eclipse.jdt.debug.eval.IEvaluationEngine;
import org.eclipse.jdt.internal.debug.eval.LocalEvaluationEngine;
import org.eclipse.jdt.ui.text.java.CompletionProposalCollector;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.i18n.internal.Messages;

/**
 * Utilities for proposals. <br/>
 * 
 * $Id$
 * 
 */
public final class ProposalUtils {

    /**
     * Constructs a new ProposalUtils.
     */
    private ProposalUtils() {
    }

    public static ContentProposalAdapterExtended getCommonProposal(Control control,
            IContentProposalProvider proposalProvider) {
        IControlContentAdapter controlContentAdapter = null;
        if (control instanceof Text) {
            controlContentAdapter = new TextContentAdapterExtended();
        } else if (control instanceof StyledText) {
            controlContentAdapter = new StyledTextContentAdapterExtended();
        } else {
            throw new IllegalArgumentException(
                    Messages.getString("ProposalUtils.CtrlProposal.ErrorMsg") + control.getClass()); //$NON-NLS-1$
        }
        final ContentProposalAdapterExtended contentProposalAdapter = getContentProposalAdapter(control,
                controlContentAdapter, proposalProvider);

        // to don't write Carriage Return when accept a proposal
        if (control instanceof StyledText) {
            final VerifyKeyListener verifyKeyListener = new VerifyKeyListener() {

                public void verifyKey(VerifyEvent verifyEvent) {
                    if (verifyEvent.character == '\r' && contentProposalAdapter.isProposalOpened()) {
                        verifyEvent.doit = false;
                    } else {
                        verifyEvent.doit = true;
                    }
                }
            };
            ((StyledText) control).addVerifyKeyListener(verifyKeyListener);
        }

        return contentProposalAdapter;
    }

    private static ContentProposalAdapterExtended getContentProposalAdapter(Control control,
            IControlContentAdapter controlContentAdapter) {
        return getContentProposalAdapter(control, controlContentAdapter, null);
    }

    private static ContentProposalAdapterExtended getContentProposalAdapter(Control control,
            IControlContentAdapter controlContentAdapter, IContentProposalProvider proposalProvider) {
        ContentProposalAdapterExtended contentProposalAdapter = null;
        try {
            KeyStroke keyStroke = KeyStroke.getInstance("Ctrl+Space");

            contentProposalAdapter = new ContentProposalAdapterExtended(control, controlContentAdapter,
                    proposalProvider, keyStroke, null);
            contentProposalAdapter.setPropagateKeys(true);
            contentProposalAdapter.setFilterStyle(ContentProposalAdapterExtended.FILTER_CUMULATIVE_ALL_START_WORDS);
            contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapterExtended.PROPOSAL_INSERT);
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        return contentProposalAdapter;
    }

    public static ContentProposalAdapterExtended getCommonProposal(Control control) {
        return getCommonProposal(control, null);
    }

    public static ContentProposalAdapterExtended getCommonProposal(CellEditor cellEditor) {
        if (cellEditor instanceof TextCellEditorWithProposal) {
            return getContentProposalAdapter(cellEditor.getControl(), new TextCellEditorContentAdapterExtended());
        } else {
            throw new IllegalArgumentException(
                    Messages.getString("ProposalUtils.CellProposal.Error") + cellEditor.getClass()); //$NON-NLS-1$
        }
    }

    /**
     * DOC amaumont Comment method "initializeJavaProposal".
     * 
     * @param compilationUnit
     */
    public static void initializeJavaProposal(ICompilationUnit compilationUnit) {

        IJavaProject javaProject = compilationUnit != null ? ((IJavaElement) compilationUnit).getJavaProject() : null;
        IEvaluationContext evaluationContext = javaProject.newEvaluationContext();

        IClassFileEvaluationEngine classFileEvaluationEngine = EvaluationManager.newClassFileEvaluationEngine(
                javaProject, null, new File(""));

        LocalEvaluationEngine localEvaluationEngine = (LocalEvaluationEngine) classFileEvaluationEngine;

        IEvaluationEngine astEvaluationEngine = EvaluationManager.newAstEvaluationEngine(javaProject, null);

        String code = null;
        try {
            code = compilationUnit.getBuffer().getContents();
        } catch (JavaModelException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // try {
        // evaluationContext.evaluateCodeSnippet(code , localEvaluationEngine, null);
        // } catch (JavaModelException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        CompletionProposalCollector completionProposalCollector = new CompletionProposalCollector(compilationUnit);
        JavaContentAssistInvocationContext contentAssistInvocationContext = new JavaContentAssistInvocationContext(
                compilationUnit);
        completionProposalCollector.setInvocationContext(contentAssistInvocationContext);

        // CompletionProposal completionProposal = CompletionProposal.create(CompletionProposal.FIELD_REF,
        // completionOffset);
        // completionProposalCollector.accept(proposal)

        CompletionContext completionContext = new CompletionContext();
        completionProposalCollector.acceptContext(completionContext);

        IJavaElement[] javaElements = null;
        try {
            javaElements = evaluationContext.codeSelect(code, 9265, code.length());
            evaluationContext.codeComplete(code, 9265, completionProposalCollector);
        } catch (JavaModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // System.out.println();
        // CompletionEngine name = new CompletionEngine();

        // System.out.println();
    }

}
// backup do not remove, it will be removed later
// @SuppressWarnings("restriction")
// public static List<ProblemWithLine> getProblems() {
//
// //
// // int length = workingCopy.getContents().length;
//
// // reconciler.getReconcilingStrategy(getContentDescription()).
// // getEditorInput().get
//
// IJavaProject javaProject = unit != null ? ((IJavaElement) unit).getJavaProject() : null;
// HashMap preferences = null;
// if (javaProject == null)
// preferences = new HashMap(JavaCore.getOptions());
// else
// preferences = new HashMap(javaProject.getOptions(true));
//
// // IRunProcessService service = (IRunProcessService)
// // GlobalServiceRegister.getDefault().getService(IRunProcessService.class);
// // IProject project = service.getProject();
//
// ICompilerRequestor compilerRequestor = new ICompilerRequestor() {
//
// public void acceptResult(CompilationResult compilationResult) {
// // do nothing
// }
// };
// NameEnvironment nameEnvironment = new NameEnvironment(javaProject);
// CompilerOptions compilerOptions = new CompilerOptions(preferences);
// DefaultProblemFactory problemFactory = new DefaultProblemFactory(Locale.getDefault());
// IErrorHandlingPolicy errorHandlingPolicy = DefaultErrorHandlingPolicies.proceedWithAllProblems();
// org.eclipse.jdt.internal.compiler.CompilationResult compilationResult = new CompilationResult(unit, 1, 1, 100);
// org.eclipse.jdt.internal.compiler.problem.ProblemReporter problemReporter = new
// ProblemReporter(DefaultErrorHandlingPolicies
// .proceedWithAllProblems(), compilerOptions, new DefaultProblemFactory());
// CompilationUnitDeclaration compilationUnitDeclaration = new CompilationUnitDeclaration(problemReporter,
// compilationResult, unit
// .getContents().length);
// Compiler compiler = new Compiler(nameEnvironment, errorHandlingPolicy, compilerOptions, compilerRequestor,
// problemFactory);
// // CompilationResult result = problemReporter.referenceContext.compilationResult();
//
// compiler.compile(new ICompilationUnit[] { (ICompilationUnit) unit });
// compiler.resolve((org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration) compilationUnitDeclaration,
// (ICompilationUnit) unit, true, true, false);
// // CompilationResult compilationResult2 = compilationUnitDeclaration.scope.referenceContext.compilationResult;
// CompilationResult compilationResult2 = compilationUnitDeclaration.compilationResult;
// CategorizedProblem[] problems3 = compilationResult2.problems;
//
// // CompilationUnitDeclaration[] unitsToProcess = compiler.unitsToProcess;
// //
// // for (int i = 0; i < unitsToProcess.length; i++) {
// // CompilationUnitDeclaration declaration = unitsToProcess[i];
// // CompilationResult compilationResult2 = declaration.compilationResult;
// // CategorizedProblem[] problems = compilationResult2.problems;
// // for (int j = 0; j < problems.length; j++) {
// // CategorizedProblem problem = problems[j];
// // System.out.println();
// // }
// //
// // }
//
// ProblemReporter problemReporterFromCompiler = compiler.problemReporter;
//
// Map options = JavaCore.getOptions();
// options.remove(JavaCore.COMPILER_TASK_TAGS); // no need to parse task tags
//
// // CompilationUnitProblemFinder.process(compilationUnitDeclaration, unit, unit.getContents(), null,
// // workingCopyOwner, problems, true, false, monitor)
//
// LookupEnvironment lookupEnvironment = new LookupEnvironment(compiler, compilerOptions, problemReporter,
// nameEnvironment);
// compilationUnitDeclaration.scope = new CompilationUnitScope(compilationUnitDeclaration, lookupEnvironment);
// //
// // if (compilationUnitDeclaration.scope != null) {
// // compilationUnitDeclaration.resolve();
// // }
// compilationUnitDeclaration.resolve();
//
// CategorizedProblem[] allProblems = compilationResult.getAllProblems();
//
// CategorizedProblem[] problems = compilationResult.getProblems();
//
// final ArrayList<ProblemWithLine> iproblems = new ArrayList<ProblemWithLine>();
//
// // create requestor for accumulating discovered problems
// IProblemRequestor problemRequestor = new IProblemRequestor() {
//
// public void acceptProblem(IProblem problem) {
// if (problem.isError()) {
// System.out.println(problem.getID() + ": " + problem.getMessage());
// String line = extractLine(unit.getContents(), problem.getSourceStart());
// ProblemWithLine problemWithLine = new ProblemWithLine(problem, line);
// iproblems.add(problemWithLine);
// }
//
// }
//
// private String extractLine(char[] contents, int start) {
// // search previous cr
// int previousCrIndex = -1;
// int nextCrIndex = -1;
// for (int i = start, j = start; i >= 0 && j < contents.length; i--, j++) {
// if (previousCrIndex == -1) {
// if (contents[i] == '\n') {
// previousCrIndex = i + 1;
// }
// }
// if (i == 0 && previousCrIndex == -1) {
// previousCrIndex = 0;
// }
//
// if (nextCrIndex == -1) {
// if (contents[j] == '\n' || contents[j] == '\r') {
// nextCrIndex = j;
// }
// }
//
// if (j == contents.length - 1 && nextCrIndex == -1) {
// nextCrIndex = contents.length;
// }
//
// if (previousCrIndex != -1 && nextCrIndex != -1) {
// break;
// }
//
// }
//
// return String.copyValueOf(contents, previousCrIndex, nextCrIndex - previousCrIndex);
//
// }
//
// public void beginReporting() {
// }
//
// public void endReporting() {
// }
//
// public boolean isActive() {
// return true;
// } // will detect problems if active
// };
//
// // use working copy to hold source with error
// // ((IOpenable)workingCopy).getBuffer().setContents("public class X extends Zork {}");
// org.eclipse.jdt.core.ICompilationUnit workingCopy;
// try {
// workingCopy = ((org.eclipse.jdt.core.ICompilationUnit) unit).getWorkingCopy(new WorkingCopyOwner() {
// }, problemRequestor, null);
// ((IOpenable) workingCopy).getBuffer().setContents(unit.getContents());
// // workingCopy.reconcile(1, true, null, null);
// // workingCopy.reconcile(true, null);
// } catch (JavaModelException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// // trigger reconciliation
//
// return iproblems;
// // return problemReporterFromCompiler.;
// //
// // unit.getC
//
// // reconciler.
// // strategy.reconcile(new Region(0, this.getDocumentProvider().getDocument(getEditorInput()).getLength()));
// // strategy.getReconcilingStrategies()[0].
// }
