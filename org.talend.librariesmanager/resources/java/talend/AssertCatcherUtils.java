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
package routines.system;

import java.util.Date;

public class AssertCatcherUtils {

    public class AssertCatcherMessage {

        private Date date;

        private String pid;

        private String projet;

        private String job;

        private String langage;

        private String origin;

        private String status;

        private String substatus;

        private String description;

        public AssertCatcherMessage(String pid, String projet, String job, String langage, String origin,
                String status, String substatus, String description) {
            this.date = java.util.Calendar.getInstance().getTime();
            this.pid = pid;
            this.projet = projet;
            this.job = job;
            this.langage = langage;
            this.origin = origin;
            this.status = status;
            this.substatus = substatus;
            this.description = description;
        }

        /**
         * Getter for date.
         * 
         * @return the date
         */
        public Date getDate() {
            return this.date;
        }

        /**
         * Sets the date.
         * 
         * @param date the date to set
         */
        public void setDate(Date date) {
            this.date = date;
        }

        /**
         * Getter for description.
         * 
         * @return the description
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * Sets the description.
         * 
         * @param description the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Getter for job.
         * 
         * @return the job
         */
        public String getJob() {
            return this.job;
        }

        /**
         * Sets the job.
         * 
         * @param job the job to set
         */
        public void setJob(String job) {
            this.job = job;
        }

        /**
         * Getter for langage.
         * 
         * @return the langage
         */
        public String getLangage() {
            return this.langage;
        }

        /**
         * Sets the langage.
         * 
         * @param langage the langage to set
         */
        public void setLangage(String langage) {
            this.langage = langage;
        }

        /**
         * Getter for origin.
         * 
         * @return the origin
         */
        public String getOrigin() {
            return this.origin;
        }

        /**
         * Sets the origin.
         * 
         * @param origin the origin to set
         */
        public void setOrigin(String origin) {
            this.origin = origin;
        }

        /**
         * Getter for pid.
         * 
         * @return the pid
         */
        public String getPid() {
            return this.pid;
        }

        /**
         * Sets the pid.
         * 
         * @param pid the pid to set
         */
        public void setPid(String pid) {
            this.pid = pid;
        }

        /**
         * Getter for projet.
         * 
         * @return the projet
         */
        public String getProjet() {
            return this.projet;
        }

        /**
         * Sets the projet.
         * 
         * @param projet the projet to set
         */
        public void setProjet(String projet) {
            this.projet = projet;
        }

        /**
         * Getter for status.
         * 
         * @return the status
         */
        public String getStatus() {
            return this.status;
        }

        /**
         * Sets the status.
         * 
         * @param status the status to set
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * Getter for substatus.
         * 
         * @return the substatus
         */
        public String getSubstatus() {
            return this.substatus;
        }

        /**
         * Sets the substatus.
         * 
         * @param substatus the substatus to set
         */
        public void setSubstatus(String substatus) {
            this.substatus = substatus;
        }

    }

    java.util.List<AssertCatcherMessage> messages = new java.util.ArrayList<AssertCatcherMessage>();

    public void addMessage(String pid, String projet, String job, String langage, String origin,
            String status, String substatus, String description) {
        AssertCatcherMessage lcm = new AssertCatcherMessage(pid, projet, job, langage, origin, status, substatus,
                description);
        messages.add(lcm);
    }

    public java.util.List<AssertCatcherMessage> getMessages() {
        java.util.List<AssertCatcherMessage> messagesToSend = new java.util.ArrayList<AssertCatcherMessage>();
        for (AssertCatcherMessage acm : messages) {
            messagesToSend.add(acm);
        }
        messages.clear();
        return messagesToSend;
    }
}