For Software Architecture, I would like us to cooperate with the help of github and git to manager our source codes for our SAAS architecture project. 

Using git/github requires us to have git utility installed on our local operating system. Also, we need have a github.com account to push our code to and pull down other codes from github.com 

After installation of git tool, the following files need be put under home directory:
      - .gitconfig
      - git.template

The two files are available under the git repository. After installation, run the following commands:
	0, mkdir git; cd git;
    1, git clone git@github.com:AUT-MCIS-ORG/saas-on-osgi.git
    2, cd saas-on-osgi
    3, cp .gitconfig ~/
    4, cp git.template ~/
    5, git config --global commit.template ~/git.template
    6, update the ~/.gitconfig file with your name and email address

For each one of us, I would like us to work independently on each branch, so please execute following commands:
    1, git checkout -b bb/<name>
    2, git push --set-upstream origin bb/<name>

On our branches, we can update any files or create new files for our project. Once we finish one logical block of codes, either in one file, or in multiple files, use the following commands to submit them as one commit to github.com
   1, git add .
   2, git commit -a
      - the above command will open an editor <vi/emacs> depending on the editor we chose/set in the ~/.gitconfig file
      -- [core]
      --	editor = emacs
      - Make sure to input the subject line, and the message body. 

   3, git push
      - to push the local changes/commit to github.com server, so that others can see it. 


