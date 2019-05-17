package com.example.demo;

import java.io.File;
import java.io.PrintWriter;

import javax.ws.rs.GET;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DemoContoller {
	
	@GET
	@RequestMapping("/github")
	@ResponseBody
	public String getGithub() throws Exception {

		GitHub github = GitHub.connectUsingPassword("amalkurup", "Amlu_1234");
		GHRepository aGHRepository = github.createRepository("nest" + System.currentTimeMillis()).create();
		String aAbsoluteProjUrl = aGHRepository.getHttpTransportUrl();
		CredentialsProvider aCredentialsProvider = new UsernamePasswordCredentialsProvider("amalkurup",
				"Amlu_1234".toCharArray());

		File file = new File(System.getProperty("user.home") + "/.gitconfig");
		if (!file.exists()) {
			PrintWriter writer = new PrintWriter(file);
			writer.println("[http]");
			writer.println("sslverify = false");
			writer.close();
		}

		CloneCommand aClone = Git.cloneRepository().setURI(aAbsoluteProjUrl);

		File f = new File("/tmp/repos/mock7" + System.currentTimeMillis());
		if (!f.exists()) {
			f.mkdir();
		}

		Git git = aClone.setCredentialsProvider(aCredentialsProvider).setDirectory(f).call();
		FileUtils.copyDirectory(new File(System.getProperty("user.home")), f);
		git.add().addFilepattern(".").call();
		git.commit().setCommitter("default", "default").setMessage("Adding initial code structure").call();
		git.push().setCredentialsProvider(aCredentialsProvider).setRemote(aAbsoluteProjUrl).call();

		return aAbsoluteProjUrl;
	}

}
