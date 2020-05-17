<?php
/**
 * Created by IntelliJ IDEA.
 * User: duclo
 * Date: 24/04/2018
 * Time: 21:44
 */

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use App\Entity\User;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Routing\Annotation\Route;
use FOS\RestBundle\Controller\Annotations as FOSRest;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use FOS\RestBundle\View\View;

/**
 * @Route("/api")
 */
class UserController extends AbstractController
{
    /**
     * @Security("is_granted('ROLE_SUPER_ADMIN')")
     * @FOSRest\Post("/auth/admin/users")
     */
    public function register(Request $request, UserPasswordEncoderInterface $encoder)
    {
        $em = $this->getDoctrine()->getManager();

        $username =  $request->request->get('username');
        $password =  $request->request->get('password');

        $user = new User($username);
        $user->setPassword($encoder->encodePassword($user, $password));
        $user->setRoles('ROLE_USER_CLASSIC');

        $em->persist($user);
        $em->flush();

        return View::create($user, Response::HTTP_OK , []);
    }

    /**
     * @Security("is_granted('ROLE_SUPER_ADMIN')")
     * @FOSRest\Get("/auth/admin/users")
     */
    public function listUser(Request $request)
    {
        $users = $this->getDoctrine()->getManager()
            ->createQueryBuilder()
            ->select('u.id', 'u.username', 'u.roles', 'u.isActive AS is_active')
            ->from('App:User', 'u')
            ->getQuery()
            ->getResult();

        return View::create($users, Response::HTTP_OK , []);
    }

    /**
     * @Security("is_granted('ROLE_SUPER_ADMIN')")
     * @FOSRest\Delete("/auth/admin/users/{id}")
     *
     */
    public function removeUser(Request $request)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $user = $entityManager->getRepository(User::class)->find($request->get('id'));
        $entityManager->remove($user);
        $entityManager->flush();

        return View::create($user, Response::HTTP_OK , []);
    }
}